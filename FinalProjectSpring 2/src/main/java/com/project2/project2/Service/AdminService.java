package com.project2.project2.Service;

import com.project2.project2.Beans.Company;

import com.project2.project2.Beans.Coupon;
import com.project2.project2.Beans.Customer;
import com.project2.project2.Exceptions.CompanyUserException;
import com.project2.project2.Exceptions.CouponException;
import com.project2.project2.Exceptions.CustomerUserException;


import com.project2.project2.Util.ColorPrint;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * This class is in charge of all the logic of all the function of Admin type.
 * It has 4 attributes . login method that gives access to only authorized user.
 *
 */

@Service
public class AdminService extends ClientService {
    @Autowired
    private CustomerService cus;
    @Autowired
    private CompanyService cs;
    private final String EMAIL = "admin@admin.com";
    private final String PASSWORD = "admin";

    @Override
    public boolean login(String email, String password) {
        if(email.equals(EMAIL)&&password.equals(PASSWORD)){
            System.out.println(ColorPrint.ANSI_BLUE+"Welcome back admin!"+ColorPrint.ANSI_RESET);
        }else{
            System.out.println("somethin went wrong!");
        }
        return email.equals(EMAIL)&&password.equals(PASSWORD);
    }
    public void addCompany(Company company) {
        try {
            companyRepo.save(company);
            System.out.println("Company added successfully");
        }catch (DataIntegrityViolationException e){
            System.out.println("Company already exists!");
        }
    }
    public void updateCompany(Company company) throws CompanyUserException {
        if(!companyRepo.existsById(company.getId())){
            throw new CompanyUserException("Company does not exists!");
        }
        Company updateCompany = companyRepo.findById(company.getId());
        updateCompany.setEmail(company.getEmail());
        updateCompany.setPassword(company.getPassword());
        companyRepo.saveAndFlush(updateCompany);
        System.out.println("Updated done!");
    }
    public void deleteCompany(int companyID) throws CouponException {
        List<Coupon>coupons = companyRepo.findById(companyID).getCoupons();
        for(Coupon item:coupons){
            cus.removeCoupon(item);
            cs.deleteCoupon(item.getId());

        }
        companyRepo.deleteById(companyID);
        System.out.println("Company deleted!");
    }


    public List<Company>getAllCompanies(){
        return companyRepo.findAll();
    }
    public Company getCompanyById(int companyId){
        return companyRepo.findById(companyId);
    }
    public void addCustomer(Customer customer) {
        try {
            customerRepo.save(customer);
            System.out.println("Customer added successfully ");
        }catch (DataIntegrityViolationException e){
            System.out.println("Customer already exists!");
        }
    }
    public void updateCustomer(Customer customer) throws CustomerUserException {
        if (!customerRepo.existsById(customer.getId())) {
            throw new CustomerUserException("Customer not found");
        }
        Customer updateLecturer = customerRepo.findById(customer.getId());
        updateLecturer.setFirstName(customer.getFirstName());
        updateLecturer.setLastName(customer.getLastName());
        updateLecturer.setEmail(customer.getEmail());
        updateLecturer.setPassword(customer.getPassword());
        customerRepo.saveAndFlush(updateLecturer);
        System.out.println("Updated done!");
    }
    public void deleteCustomer(int customerID) {
        customerRepo.deleteById(customerID);
        System.out.println("Customer deleted!");
    }

    public void deleteCoupon(int id) {
        couponRepo.deleteById(id);

    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer getOneCustomer(int customerID) {
        return customerRepo.findById(customerID);
    }




}
