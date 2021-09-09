package com.project2.project2.Thread;
import com.project2.project2.Repositories.CouponRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This is thread class that execute an operate behind the scene for the project.
 * using  cron job to make it work in specific day and time.
 * this class has 3 attributes  because we operate on the
 * coupons and delete all the coupons that their dates has passed.
 */

@Component
@EnableAsync
@EnableScheduling
@RequiredArgsConstructor
public class CouponExpirationDailyJob {
    private final  CouponRepo cus;
    /**
     * This method is running in the background. and every day at specific time checks all the coupons date from our database table.
     * and every day it is erasing all the coupons that expired.
     */

    @Async
    @Scheduled(cron = "0 51 15 * * *",zone = "Asia/Jerusalem")
    public void deleteExpiredCoupons() {
          cus.deletePurchasedCouponAfterDate(new java.sql.Date(System.currentTimeMillis()));
          cus.deleteByEndDateBefore(new java.sql.Date(System.currentTimeMillis()));
        System.out.println("Expired coupon has deleted!");
        }
    }
