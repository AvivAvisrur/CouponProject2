package com.project2.project2.Beans;

import lombok.*;



import javax.persistence.*;

import java.util.List;

/**
 * This class is in charge of creating, updating and getting  customers instance and values of it
 * This class has 6 attributes from types String, Integer and ArrayList. and methods as written below
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false,unique = true,length = 40)
    private String email;

    @Column(nullable = false)
    private String password;


    @Singular
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Coupon> coupons;
}
