package com.project2.project2.Service;

import com.project2.project2.Beans.Category;
import com.project2.project2.Beans.Coupon;
import com.project2.project2.Beans.Customer;
import com.project2.project2.Exceptions.CouponException;
import com.project2.project2.Exceptions.CustomerUserException;
import com.project2.project2.Exceptions.DuplicationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService extends ClientService {
    private int customerId;



  @Override
    public boolean login(String email, String password) throws CustomerUserException {
    try {
      customerId = customerRepo.findByEmailAndPassword(email, password).getId();
      System.out.println("Login successful");
      return true;
    }catch (NullPointerException e){
      System.out.println("Customer is not exists!");
    }
    return false;
    }

    public void purchaseCoupon(int couponID) throws CouponException, DuplicationException{
    Coupon coupon = couponRepo.findById(couponID);
      if(coupon.getAmount()<1){
        throw new CouponException("Coupon is out of stock!");
      }
      if(coupon.getEndDate().before(new java.sql.Date(System.currentTimeMillis()))){
        throw new CouponException("Coupon has expired!");
      }
      Customer updateCustomer = customerRepo.findById(customerId);
      updateCustomer.getCoupons().add(coupon);
      coupon.setAmount(coupon.getAmount()-1);
      couponRepo.saveAndFlush(coupon);
      customerRepo.saveAndFlush(updateCustomer);
    }



    public void removeCoupon(Coupon coupon){
      Customer updateCustomer = customerRepo.findById(customerId);
      int index = updateCustomer.getCoupons().indexOf(coupon);
      updateCustomer.getCoupons().remove(index);
      coupon.setAmount(coupon.getAmount()+1);
      couponRepo.saveAndFlush(coupon);
      customerRepo.saveAndFlush(updateCustomer);


    }

    public List<Coupon> getCustomerCouponsByCategory(Category category){
      return couponRepo.findCutomersCouponByCategory(customerId,category.ordinal());
    }

    public List<Coupon> getAllPurchasedCoupons() {
        return customerRepo.findById(customerId).getCoupons();
    }

    public List<Coupon>getCustomerCouponsByMaxPrice(double maxPrice){
      return customerRepo.findById(customerId).getCoupons().stream().filter((coupon -> coupon.getPrice()<=maxPrice)).collect(Collectors.toList());
    }
    public Customer getCustomerDetails(){
      return customerRepo.findById(customerId);
    }
}
