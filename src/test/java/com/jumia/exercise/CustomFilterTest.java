package com.jumia.exercise;

import com.jumia.exercise.dto.SearchResultDto;
import com.jumia.exercise.entities.Customer;
import com.jumia.exercise.service.CustomFilterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class CustomFilterTest {

    @Autowired CustomFilterService customFilterService;


    List<SearchResultDto> ethiopiaValidation = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        System.out.println("Setting up test beans..");
        ethiopiaValidation.add( SearchResultDto.builder()
                .id(22)
                .phoneNumber("(251) 9773199405")
                .phoneStatus("Not valid")
                .countryCode("251")
                .country("Ethiopia")
                .customerName("shop23 sales")
                .build());
        ethiopiaValidation.add( SearchResultDto.builder()
                .id(25)
                .phoneNumber("(251) 9119454961")
                .phoneStatus("Not valid")
                .countryCode("251")
                .country("Ethiopia")
                .customerName("ZEKARIAS KEBEDE")
                .build());
    }

    @Test
    public void searchByCountryStatus() {
        assertThat(customFilterService.searchByCountryStatus("Cameroon","Valid")).isEmpty();
          }

    @Test
    public void validPhoneNumber()  {
        String status = customFilterService.validatePhoneNumber("(212) 698054317");
        assertEquals("Valid",status);
    }

    @Test
    public void notValidPhoneNumber() {
        String status = customFilterService.validatePhoneNumber("(212) 69805431712");
        assertThat(status).isEqualTo("Not valid");
    }

    @Test
    public void ethiopiaValidation() {
        assertThat(customFilterService.searchByCountryStatus("Ethiopia","Not valid")).isEqualTo(ethiopiaValidation);
    }
}
