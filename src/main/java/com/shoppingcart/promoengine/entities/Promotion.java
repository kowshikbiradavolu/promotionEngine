package com.shoppingcart.promoengine.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public Integer promoId;
    @Column
    private String skuId;
    @Column
    public String promoType;
    @Column
    public String mappingSkuId;
    @Column
    private Integer promoItemCount;
    @Column
    private Long promoPrice;
    @Column
    private Long promoDiscountPercent;


}
