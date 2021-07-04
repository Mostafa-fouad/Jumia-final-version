package com.jumia.exercise.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "COUNTRY_CODE_LKP")
public class CountryCodeLKP {
    @Id
    @Column(name = "COUNTRY_CODE")
    String countryCode;

    @Column(name = "COUNTRY")
    String country;

}
