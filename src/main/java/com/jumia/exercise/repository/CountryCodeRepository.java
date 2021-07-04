package com.jumia.exercise.repository;

import com.jumia.exercise.entities.CountryCodeLKP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CountryCodeRepository extends JpaRepository<CountryCodeLKP,String> {

    @Transactional
    @Query(value = "select c.country from CountryCodeLKP c")
    List<String> getCountryNames();

    @Transactional
    @Query(value = "select c.countryCode from CountryCodeLKP c where c.country = :countryName")
    String getCountryCodeByCountryName(@Param("countryName") String countryName);

}
