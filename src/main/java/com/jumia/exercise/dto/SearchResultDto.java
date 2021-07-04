package com.jumia.exercise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SearchResultDto {
    private Integer id;
    private String customerName;
    private String countryCode;
    private String country;
    private String phoneNumber;
    private String phoneStatus;
}
