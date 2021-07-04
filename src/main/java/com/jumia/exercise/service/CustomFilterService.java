package com.jumia.exercise.service;

import com.jumia.exercise.dto.SearchResultDto;
import com.jumia.exercise.entities.Customer;
import com.jumia.exercise.repository.CountryCodeRepository;
import com.jumia.exercise.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomFilterService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CountryCodeRepository countryCodeRepository;
    private String countryCode;
    private static Map<String, String> countryCodeMap;
    private static Map<String, String> countryRegexMap;

    static { //Initializing Lookups
        countryCodeMap = new HashMap<>();
        countryRegexMap = new HashMap<>();
        countryCodeMap.put("237","Cameroon");
        countryCodeMap.put("251","Ethiopia");
        countryCodeMap.put("212","Morocco");
        countryCodeMap.put("258","Mozambique");
        countryCodeMap.put("256","Uganda");
        countryRegexMap.put("Cameroon","\\(237\\)\\?[2368]\\d{7,8}$");
        countryRegexMap.put("Ethiopia","\\(251\\)\\ ?[1-59]\\d{8}$");
        countryRegexMap.put("Morocco","\\(212\\)\\ ?[5-9]\\d{8}$");
        countryRegexMap.put("Mozambique","\\(258\\)\\ ?[28]\\d{7,8}$");
        countryRegexMap.put("Uganda","256\\)\\ ?\\d{9}$");
    }

    public List<Customer> getCustomer(){
        return customerRepository.findAll();
    }
    public List<String> getCountryNames(){
        return countryCodeRepository.getCountryNames();
    }
    public List<Customer> getCustomersByCountryCode (String countryName){
        countryCode = countryCodeRepository.getCountryCodeByCountryName(countryName);
        return customerRepository.getCustomerByCountryCode(countryCode);
    }

    public List<SearchResultDto> initTable (){
        List<SearchResultDto> searchResultList = new ArrayList<>();
        List<Customer> customers = getCustomer();
        for (Customer customer : customers){
            searchResultList.add(SearchResultDto.builder()
                    .id(customer.getId())
                    .customerName(customer.getName())
                    .phoneNumber(customer.getPhone())
                    .country(countryCodeMap.get(customer.getPhone().substring(1,4)))
                    .countryCode(customer.getPhone().substring(1,4))
                    .phoneStatus(validatePhoneNumber(customer.getPhone()))
                    .build());
        }
        return searchResultList;
    }

    public List<SearchResultDto> searchByCountry(String countryName){
        List<SearchResultDto> searchResultList = new ArrayList<>();
        List<Customer> customers = getCustomersByCountryCode(countryName);
        for (Customer customer : customers){
            searchResultList.add(SearchResultDto.builder()
                    .id(customer.getId())
                    .customerName(customer.getName())
                    .phoneNumber(customer.getPhone())
                    .country(countryCodeMap.get(customer.getPhone().substring(1,4)))
                    .countryCode(countryCode)
                    .phoneStatus(validatePhoneNumber(customer.getPhone()))
                    .build());
        }
        return searchResultList;
    }

    public List<SearchResultDto> searchByStatus(String statusType){
        List<SearchResultDto> searchResultList = new ArrayList<>();
        List<Customer> customers = getCustomer();
        for (Customer customer : customers){
            if (validatePhoneNumber(customer.getPhone()).equals(statusType)) {
                searchResultList.add(SearchResultDto.builder()
                        .id(customer.getId())
                        .customerName(customer.getName())
                        .phoneNumber(customer.getPhone())
                        .country(countryCodeMap.get(customer.getPhone().substring(1,4)))
                        .countryCode(customer.getPhone().substring(1, 4))
                        .phoneStatus(validatePhoneNumber(customer.getPhone()))
                        .build());
            }
        }
       return searchResultList;
    }

    public List<SearchResultDto> searchByCountryStatus (String countryName , String statusType){
        List<SearchResultDto> searchResultList = new ArrayList<>();
        List<Customer> customers = getCustomer();
        for (Customer customer : customers){
            if (validatePhoneNumber(customer.getPhone()).equals(statusType)
                    && countryCodeMap.get(customer.getPhone().substring(1,4)).equals(countryName)){
                searchResultList.add(SearchResultDto.builder()
                        .id(customer.getId())
                        .customerName(customer.getName())
                        .country(countryName)
                        .phoneNumber(customer.getPhone())
                        .countryCode(customer.getPhone().substring(1,4))
                        .phoneStatus(statusType)
                        .build());
            }
        }
        return searchResultList;
    }

    public String validatePhoneNumber (String phoneNumber){

        String country = countryCodeMap.get(phoneNumber.substring(1,4));
        Pattern p = Pattern.compile(countryRegexMap.get(country));
        Matcher m = p.matcher(phoneNumber);
        if( m.matches() == true) return "Valid";
        else return "Not valid";

    }
}
