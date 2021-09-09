package com.project2.project2.CLR;

import com.project2.project2.Beans.Category;
import com.project2.project2.Exceptions.CouponException;
import com.project2.project2.Login.ClientType;
import com.project2.project2.Login.LoginManager;
import com.project2.project2.Service.CustomerService;
import com.project2.project2.Util.ColorPrint;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(3)
public class CustomerCLR implements CommandLineRunner {
    private final LoginManager loginManager;
    @Override
    public void run(String... args) throws Exception {
        System.out.println(ColorPrint.ANSI_YELLOW+"========================Customer========================");
        System.out.println(ColorPrint.ANSI_RED +"Successful Login (Customer) : "+ColorPrint.ANSI_RESET);
        CustomerService cs = (CustomerService)loginManager.login("AvivAvisror@gmail.com","12345", ClientType.CUSTOMER);
        System.out.println(ColorPrint.ANSI_RED +"UnSuccessful Login (Customer) : "+ColorPrint.ANSI_RESET);
        CustomerService cs1 = (CustomerService)loginManager.login("Aviv@gmail.com","123456", ClientType.CUSTOMER);
        System.out.println(ColorPrint.ANSI_RED +" Purchase Coupon  : "+ColorPrint.ANSI_RESET);
        try {
            cs.purchaseCoupon(1);
        }catch (CouponException e){
            System.out.println(e.getMessage());
        }
        System.out.println(ColorPrint.ANSI_RED +" Get customer Details  : "+ColorPrint.ANSI_RESET);
        System.out.println(cs.getCustomerDetails());
        System.out.println(ColorPrint.ANSI_RED +" Get All customer Coupons Details  : "+ColorPrint.ANSI_RESET);
        System.out.println(cs.getAllPurchasedCoupons());
        System.out.println(ColorPrint.ANSI_RED +" Get all customer Coupons by Category Details  : "+ColorPrint.ANSI_RESET);
        System.out.println(cs.getCustomerCouponsByCategory(Category.Food));
        System.out.println(ColorPrint.ANSI_RED +" Get all customer coupons by maxPRICE Details  : "+ColorPrint.ANSI_RESET);
        System.out.println(cs.getCustomerCouponsByMaxPrice(200));













    }
}
