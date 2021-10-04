package com.shoppingcart.promoengine.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor
public class Item {

    @Id
    @Column
    private String skuId;
    @Column
    private Long price;

}
