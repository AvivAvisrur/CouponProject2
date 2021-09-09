package com.project2.project2.Service;

import com.project2.project2.Exceptions.CompanyUserException;
import com.project2.project2.Exceptions.CustomerUserException;
import com.project2.project2.Repositories.CompanyRepo;
import com.project2.project2.Repositories.CouponRepo;
import com.project2.project2.Repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;



public abstract class ClientService {
    @Autowired
    protected CustomerRepo customerRepo;
    @Autowired
    protected CouponRepo couponRepo;
    @Autowired
    protected CompanyRepo companyRepo;

    public abstract boolean login (String email, String password) throws CompanyUserException, CustomerUserException;

}
