package com.project2.project2.Repositories;


import com.project2.project2.Beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    Customer findById(int id);
    boolean existsCustomerByEmailAndPassword(String email, String password);
    Customer findByEmailAndPassword(String email, String password);


}
