package com.project2.project2.CLR;

import com.project2.project2.Beans.Company;
import com.project2.project2.Beans.Customer;
import com.project2.project2.Login.ClientType;
import com.project2.project2.Login.LoginManager;
import com.project2.project2.Service.AdminService;
import com.project2.project2.Util.ColorPrint;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class AdminCLR implements CommandLineRunner {
    private final LoginManager loginManager;


    @Override
    public void run(String... args) throws Exception {
        System.out.println(ColorPrint.ANSI_YELLOW+"========================ADMIN========================");
        System.out.println(ColorPrint.ANSI_RED +"Successful Login : "+ColorPrint.ANSI_RESET);
        AdminService as = (AdminService) loginManager.login("admin@admin.com","admin",ClientType.ADMINISTRATOR);
        System.out.println(ColorPrint.ANSI_RED +"UnSuccessful Login : "+ColorPrint.ANSI_RESET);
        AdminService as1 = (AdminService) loginManager.login("admin@gmail.com","admin",ClientType.ADMINISTRATOR);
        System.out.println(ColorPrint.ANSI_RED +"Add company : "+ColorPrint.ANSI_RESET);
        as.addCompany(Company.builder().email("Microsoft@Microsoft.com").name("Microsoft ltd").password("123456").build());
        as.addCompany(Company.builder().email("intel@intel.com").name("Intel ltd").password("123456").build());
        System.out.println(ColorPrint.ANSI_RED +"trying Add same company : "+ColorPrint.ANSI_RESET);
        as.addCompany(Company.builder().email("Microsoft@Microsoft.com").name("Microsoft ltd").password("123456").build());
        System.out.println(ColorPrint.ANSI_RED +"Add Customer: "+ColorPrint.ANSI_RESET);
        as.addCustomer(Customer.builder().email("AvivAvisror@gmail.com").firstName("Aviv").lastName("Avisror").password("123456").build());
        as.addCustomer(Customer.builder().email("Rachel@gmail.com").firstName("Rachel").lastName("Lugassi").password("123456").build());
        System.out.println(ColorPrint.ANSI_RED +"trying Add same Customer : "+ColorPrint.ANSI_RESET);
        as.addCustomer(Customer.builder().email("AvivAvisror@gmail.com").firstName("Aviv").lastName("Avisror").password("123456").build());
        System.out.println(ColorPrint.ANSI_RED +"Update Customer : "+ColorPrint.ANSI_RESET);
        as.updateCustomer(Customer.builder().id(1).email("AvivAvisror@gmail.com").lastName("Avisror").firstName("Aviv").password("12345").build());
        System.out.println(ColorPrint.ANSI_RED +"Update Company : "+ColorPrint.ANSI_RESET);
        as.updateCompany(Company.builder().id(1).email("Mirosoft@gmail.com").name("aviv").password("1234567").build());
        System.out.println(ColorPrint.ANSI_RED +"Claim All companies : "+ColorPrint.ANSI_RESET);
        System.out.println(as.getAllCompanies());
        System.out.println(ColorPrint.ANSI_RED +"Claim All customers :  "+ColorPrint.ANSI_RESET);
        System.out.println(as.getAllCustomers());
        System.out.println(ColorPrint.ANSI_RED +"Claim One customers :  "+ColorPrint.ANSI_RESET);
        System.out.println(as.getOneCustomer(2));
        System.out.println(ColorPrint.ANSI_RED +"Claim One company :  "+ColorPrint.ANSI_RESET);
        System.out.println(as.getCompanyById(2));











    }
}

