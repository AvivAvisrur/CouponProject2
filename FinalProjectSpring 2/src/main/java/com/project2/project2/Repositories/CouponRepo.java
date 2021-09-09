package com.project2.project2.Repositories;

import com.project2.project2.Beans.Category;
import com.project2.project2.Beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.util.List;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Integer> {
    Coupon findById(int id);
    void deleteById(int id);

    @Modifying
    @Query(value = "SELECT b.* FROM GrouponSystem1.customer_coupons AS a LEFT join GrouponSystem1.coupon as b ON a.coupons_id = b.id WHERE a.customers_id =:userId AND b.category =:categoryId", nativeQuery = true)
    List<Coupon>findCutomersCouponByCategory(@Param("userId")int userId,@Param("categoryId")int categoryId);
    List<Coupon> findCouponsByCompanyIdAndCategory( int userId,Category category);


    @Transactional
    @Query(value = "SELECT amount FROM coupon WHERE id=:couponId",nativeQuery = true)
    int isInStock( int couponId);
    List<Coupon> findByCompanyIdAndPriceLessThanEqual(int companyID,double MaxPrice);
    @Modifying
    @Transactional
    @Query(value="DELETE FROM customer_coupons c WHERE EXISTS(SELECT * FROM coupon co WHERE c.coupons_id=co.id AND co.end_date < :date)", nativeQuery = true)
    void deletePurchasedCouponAfterDate(Date date);
    @Transactional
    void deleteByEndDateBefore(Date date);


}
