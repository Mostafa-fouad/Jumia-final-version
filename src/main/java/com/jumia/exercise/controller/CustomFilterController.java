package com.jumia.exercise.controller;

import com.jumia.exercise.dto.CustomFilterDto;
import com.jumia.exercise.dto.SearchResultDto;
import com.jumia.exercise.service.CustomFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/jumia")
public class CustomFilterController {

    @Autowired
    CustomFilterService customerService;
    List<String> countries;


    @GetMapping("/custom-filtration")
    public String getCountries (Model theModel){
        CustomFilterDto customFilterDto = new CustomFilterDto();
        List<SearchResultDto> searchResultList = customerService.initTable();
        countries = customerService.getCountryNames();
        /*
        Binding initialized variables to the Front-End
         */
        theModel.addAttribute("searchResultList",searchResultList);
        theModel.addAttribute("countries",countries);
        theModel.addAttribute("customFilterDto",customFilterDto);
        return "custom-filtration";
    }

    @PostMapping("/display-filtration")
    public String submitAction (@ModelAttribute("customFilterDto") CustomFilterDto customFilterDto,
                                Model theModel){
        String countryName = customFilterDto.getCountryName();
        String status = customFilterDto.getStatus();
        List<SearchResultDto> searchResultList;
        /*
        Validations on user inputs before processing the request
         */
        if (!countryName.equals("null") && status.equals("null"))
            //search by country
            searchResultList = customerService.searchByCountry(countryName);
        else if (countryName.equals("null") && !status.equals("null"))
            //search by status
            searchResultList = customerService.searchByStatus(status);
        else if (!countryName.equals("null") && !status.equals("null"))
            //search by both options
            searchResultList = customerService.searchByCountryStatus(countryName,status);
        else //get all customers
            searchResultList = customerService.initTable();

        /*
        Binding results to the Front-End
         */
        theModel.addAttribute("countries",countries);
        theModel.addAttribute("searchResultList",searchResultList);
        theModel.addAttribute("country",countryName);
        theModel.addAttribute("status",status);
        return "custom-filtration";
    }
}
