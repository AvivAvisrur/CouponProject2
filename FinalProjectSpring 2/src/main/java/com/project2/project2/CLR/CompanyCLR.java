package com.project2.project2.CLR;

import com.project2.project2.Beans.Category;
import com.project2.project2.Beans.Coupon;
import com.project2.project2.Exceptions.CouponException;
import com.project2.project2.Login.ClientType;
import com.project2.project2.Login.LoginManager;
import com.project2.project2.Service.CompanyService;
import com.project2.project2.Util.ColorPrint;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@RequiredArgsConstructor
@Order(2)
public class CompanyCLR implements CommandLineRunner {
    private final LoginManager loginManager;
    @Override
    public void run(String... args) throws Exception {
        System.out.println(ColorPrint.ANSI_YELLOW+"========================Company========================");
        System.out.println(ColorPrint.ANSI_RED +"Successful Login (Company) : "+ColorPrint.ANSI_RESET);
        CompanyService cs = (CompanyService)loginManager.login("intel@intel.com","123456", ClientType.COMPANY);
        System.out.println(ColorPrint.ANSI_RED +"UnSuccessful Login (Company) : "+ColorPrint.ANSI_RESET);
        CompanyService cs1 = (CompanyService)loginManager.login("intel@gmail.com","123456", ClientType.COMPANY);
        System.out.println(ColorPrint.ANSI_RED +"Add coupon to company : "+ColorPrint.ANSI_RESET);
        cs.addCoupon(Coupon.builder().amount(3).category(Category.Food).description("mcNuggets").image("weqweqwe").price(50).startDate(Date.valueOf("2021-05-20")).endDate(Date.valueOf("2021-09-30")).title("Coupon food").build());
        cs.addCoupon(Coupon.builder().amount(3).category(Category.Food).description("dsadasd").image("weqweqwe").price(50).startDate(Date.valueOf("2021-05-20")).endDate(Date.valueOf("2021-07-30")).title("Coupon ").build());
        System.out.println(ColorPrint.ANSI_RED +"Add another coupon with same title to company : "+ColorPrint.ANSI_RESET);
        cs.addCoupon(Coupon.builder().amount(3).category(Category.Food).description("Hamburger").image("weqweqwe").price(50).startDate(Date.valueOf("2021-05-28")).endDate(Date.valueOf("2021-09-30")).title("Coupon food").build());
        System.out.println(ColorPrint.ANSI_RED +"update Coupon : "+ColorPrint.ANSI_RESET);
        try {
            cs.updateCoupon(Coupon.builder().id(1).title("After change").endDate(Date.valueOf("2021-07-20")).startDate(Date.valueOf("2021-05-20")).price(100).category(Category.Electricity).amount(10).image(".....").description("blablabla").build());
        }catch (CouponException e){
            System.out.println(e.getMessage());
        }
        System.out.println(ColorPrint.ANSI_RED +"Get all the coupons by the category : "+ColorPrint.ANSI_RESET);
        System.out.println(cs.getCompanyCouponsByCategory(Category.Food));
        System.out.println(ColorPrint.ANSI_RED +"Get all coupon of the company : "+ColorPrint.ANSI_RESET);
        System.out.println(cs.getCompanyCoupons());
        System.out.println(ColorPrint.ANSI_RED +"Get all coupon of the price range : "+ColorPrint.ANSI_RESET);
        System.out.println(cs.getCompanyCouponsByMaxPrice(200));
        System.out.println(ColorPrint.ANSI_RED +"Get company details : "+ColorPrint.ANSI_RESET);
        System.out.println(cs.getCompanyDetails());










    }
}
