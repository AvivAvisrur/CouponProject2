package com.project2.project2.Service;


import com.project2.project2.Beans.Category;
import com.project2.project2.Beans.Company;
import com.project2.project2.Beans.Coupon;
import com.project2.project2.Beans.Customer;
import com.project2.project2.Exceptions.CompanyUserException;
import com.project2.project2.Exceptions.CouponException;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;



import java.util.List;


@Service
public class CompanyService extends ClientService{
    private int companyId;

    @Override
    public boolean login(String email, String password) throws CompanyUserException {
        try {
            companyId = companyRepo.findByEmailAndPassword(email, password).getId();
            System.out.println("Logged in successfully");
            return true;
        }catch (NullPointerException e){
            System.out.println("Company is not exists!");
        }
        return false;
    }
    public void addCoupon(Coupon coupon)  {
        try {
            coupon.setCompanyId(companyId);
            couponRepo.save(coupon);
            System.out.println("Coupon added successfully to the company!");
        }catch (DataIntegrityViolationException e){
            System.out.println("You cant have 2 titles with the same descriptions!  ");
        }
    }
    public void updateCoupon(Coupon coupon) throws CouponException {
        if(!couponRepo.existsById(coupon.getId())){
            throw new CouponException("Coupon not exists!");
        }
        Coupon updateCoupon = couponRepo.findById(coupon.getId());
        updateCoupon.setAmount(coupon.getAmount());
        updateCoupon.setCategory(coupon.getCategory());
        updateCoupon.setImage(coupon.getImage());
        updateCoupon.setDescription(coupon.getDescription());
        updateCoupon.setEndDate(coupon.getEndDate());
        updateCoupon.setStartDate(coupon.getStartDate());
        couponRepo.saveAndFlush(updateCoupon);
        System.out.println("Coupon updated!");
    }
    public void deleteCoupon(int couponID) throws CouponException {
        if (!couponRepo.existsById(couponID)) {
            throw new CouponException("This coupon does not exists");
        }
        Coupon coupon = couponRepo.findById(couponID);
        for (Customer customer : coupon.getCustomers()) {
            List<Coupon> coupons = customer.getCoupons();
            int index = coupons.indexOf(coupon);
            customer.getCoupons().remove(index);
            customerRepo.saveAndFlush(customer);

        }

        couponRepo.deleteById(couponID);
        System.out.println("Coupon deleted successfully!");

    }
    public List<Coupon> getCompanyCoupons(){
        return companyRepo.findById(companyId).getCoupons();
    }
    public List<Coupon>getCompanyCouponsByCategory(Category category){
      return  couponRepo.findCouponsByCompanyIdAndCategory(companyId,category);
    }
    public List<Coupon>getCompanyCouponsByMaxPrice(double maxprice){
        return couponRepo.findByCompanyIdAndPriceLessThanEqual(companyId,maxprice);
    }


    public Company getCompanyDetails(){
        return companyRepo.findById(companyId);
    }


}
