package com.jumia.exercise.repository;

import com.jumia.exercise.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository <Customer,Integer> {

    @Transactional
    @Query(value = "select c from Customer c where c.phone like :CountryCode%")
    List<Customer> getCustomerByCountryCode(@Param("CountryCode") String CountryCode);

}
