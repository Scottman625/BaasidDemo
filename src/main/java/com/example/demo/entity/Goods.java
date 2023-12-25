package com.example.demo.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;


    @GeneratedValue
    @Column(name="_id")
    private UUID _id;

    @Column(name = "name", nullable = false, length = 128)
    private String goods_name;

    @Column(name = "cr_user", nullable = false)
    private UUID crUser;

    @Column(name = "cr_datetime", nullable = false)
    private LocalDateTime crDatetime;

    @Column(name = "up_user", nullable = false)
    private UUID upUser;

    @Column(name = "up_datetime", nullable = false)
    private LocalDateTime upDatetime;

    @PrePersist
    public void prePersist() {
        if (_id == null) {
            _id = UUID.randomUUID();
        }
    }

    // 標準的構造函數、getter 和 setter 方法
    public Goods() {
    }

    // Getters and Setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID get_id() {
        return _id;
    }

    public void set_id(UUID _id) {
        this._id = _id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public UUID getCrUser() {
        return crUser;
    }

    public void setCrUser(UUID crUser) {
        this.crUser = crUser;
    }

    public LocalDateTime getCrDatetime() {
        return crDatetime;
    }

    public void setCrDatetime(LocalDateTime crDatetime) {
        this.crDatetime = crDatetime;
    }

    public UUID getUpUser() {
        return upUser;
    }

    public void setUpUser(UUID upUser) {
        this.upUser = upUser;
    }

    public LocalDateTime getUpDatetime() {
        return upDatetime;
    }

    public void setUpDatetime(LocalDateTime upDatetime) {
        this.upDatetime = upDatetime;
    }
}

