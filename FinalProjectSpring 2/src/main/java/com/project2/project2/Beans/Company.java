package com.project2.project2.Beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import java.util.List;

/**
 * This class is in charge of creating, updating and getting  company instance and values of it.
 * This class has 5 attributes from types String, Integer and ArrayList. and methods as written below.
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false,unique = true,length = 40)
    private String name;

    @Column(nullable = false,unique = true,length = 40)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany (cascade = CascadeType.ALL,mappedBy = "companyId",orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Coupon> coupons;
}
