package com.project2.project2.Beans;

import lombok.*;


import javax.persistence.*;
import java.sql.Date;

import java.util.List;

/**
 * this class represents a coupon
 * This class has 11 attributes from types String, Integer and ArrayList. and methods as written below.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "UniqueCompanyIdAndTitle", columnNames = { "company_id", "title" }))
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "company_id")
    private int companyId;
    @Enumerated(EnumType.ORDINAL)
    private Category category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String image;

    @ManyToMany(mappedBy = "coupons")
    @ToString.Exclude()
    private List<Customer> customers;

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Coupon)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Coupon c = (Coupon) o;
        return ((Coupon) o).id == id;
    }
}
