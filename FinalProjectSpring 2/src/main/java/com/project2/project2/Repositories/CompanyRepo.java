package com.project2.project2.Repositories;

import com.project2.project2.Beans.Company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {

    boolean existsByEmailAndPassword(String email,String password);
    Company findByEmailAndPassword(String email,String password);
    Company findById(int id);
    void deleteById(int id);

}
