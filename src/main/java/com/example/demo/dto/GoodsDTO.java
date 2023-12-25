package com.example.demo.dto;

public class GoodsDTO {
    private Integer id;

    private String goods_name;

    public GoodsDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }
}
