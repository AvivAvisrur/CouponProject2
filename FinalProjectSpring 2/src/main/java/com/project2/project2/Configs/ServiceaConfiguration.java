package com.project2.project2.Configs;


import com.project2.project2.Service.AdminService;
import com.project2.project2.Service.CompanyService;
import com.project2.project2.Service.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * This class is in charge of initializing  all the service classes  .
 */

@Configuration
public class ServiceaConfiguration {
    @Bean
    public AdminService initilaizeAdminService() {
        return new AdminService();
    }
    @Bean
    @Primary
    public CompanyService initilaizeCompanyService() {
        return new CompanyService();
    }
    @Bean
    @Primary
    public CustomerService initilaizeCustomerService() {
        return new CustomerService();
    }
}

