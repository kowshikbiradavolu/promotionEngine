# promotionEngine
Simple promotion engine for a checkout process.

#Entity Design
For Designing Promotion Engine created two entities
1. Item  -- Maintains the total list of Items
2. Promotion -- Maintain List of Promotion

In the Promotion entity added a column "promoType" to differentiate the type of promotion.
Promotion Type can be Fixed, Combined or Percent.

Example
Fixed       --   3A's -- 130
Combined    --   C+D -- 30
Percent     --   50% off for A   


#Sample Request Body
Added Sample Request Bodies for Item and Promotion create service. Also added request body for Checkout Service 

1.Item

URI : http://localhost:8080/pe-items/item
Type : Post

[{
    "skuId" : "A",
    "price": "50"
}
,{
    "skuId" : "B",
    "price": "30"
},{
    "skuId" : "C",
    "price": "20"
}
,{
    "skuId" : "D",
    "price": "15"
}]

2.Promotion

URI : http://localhost:8080/pe-promotion/promotion
Type : Post

[{
    "skuId":"A",
    "promoType":"Fixed",
    "promoItemCount":3,
    "promoPrice": 130
},{
    "skuId":"B",
    "promoType":"Fixed",
    "promoItemCount":2,
    "promoPrice": 45
},{
     "skuId":"C",
    "promoType":"Combined",
    "mappingSkuId":"D",
    "promoPrice": 30
}]

3.Checkout Service

URI  : http://localhost:8080/pe-checkout/finalCartPrice
Type : Post

[{
    "skuId":"A",
   "itemCount": 5
},{
    "skuId":"B",
    "itemCount": 5
},{
    "skuId":"C",
    "itemCount": 1
},{
    "skuId":"D",
    "itemCount": 1
}]

#Additional URL's

1. H2 Console --  http://localhost:8080/h2-console

2. Swagger -- http://localhost:8080/swagger-ui.html


