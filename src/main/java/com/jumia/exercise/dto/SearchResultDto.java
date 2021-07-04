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
    Integer id;
    String customerName;
    String countryCode;
    String country;
    String phoneNumber;
    String phoneStatus;
}
