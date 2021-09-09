package com.project2.project2.Login;


import com.project2.project2.Configs.ServiceaConfiguration;
import com.project2.project2.Exceptions.CompanyUserException;
import com.project2.project2.Exceptions.CustomerUserException;

import com.project2.project2.Service.AdminService;
import com.project2.project2.Service.ClientService;
import com.project2.project2.Service.CompanyService;
import com.project2.project2.Service.CustomerService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

/**
 * This class is responsible of the management of the login process when you enter
 * email and pass it will navigate you to the specific facade u logged in.
 *
 * This class has 1 attribute from Logic manager . because it is a singleton the instance should be null first.
 */


@Service
@RequiredArgsConstructor
public class LoginManager {

    private final ServiceaConfiguration config;
    public ClientService login(String email, String password, ClientType clientType) throws CompanyUserException, CustomerUserException {
        switch (clientType) {
            case COMPANY:
                CompanyService cf = config.initilaizeCompanyService();
                return cf.login(email, password) ? cf : null;
            case ADMINISTRATOR:
                AdminService af = config.initilaizeAdminService();
                return af.login(email, password) ? af : null;
            case CUSTOMER:
                CustomerService cuf =  config.initilaizeCustomerService();
                return cuf.login(email, password) ? cuf : null;
            default:
                return null;
        }
    }
}
