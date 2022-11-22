package com.example.springmodels.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class modelGood {
    public modelGood(String goodName, double goodPrice, int goodQuantity, Date goodSupplyDate, boolean goodDefect)
    {
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.goodQuantity = goodQuantity;
        this.goodSupplyDate = goodSupplyDate;
        this.goodDefect = goodDefect;
    }

    public modelGood() {
    }

    public Long getID_Good() {
        return ID_Good;
    }

    public void setID_Good(Long ID_Good) {
        this.ID_Good = ID_Good;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public int getGoodQuantity() {
        return goodQuantity;
    }

    public void setGoodQuantity(int goodQuantity) {
        this.goodQuantity = goodQuantity;
    }

    public Date getGoodSupplyDate() {
        return goodSupplyDate;
    }

    public void setGoodSupplyDate(Date goodSupplyDate) {
        this.goodSupplyDate = goodSupplyDate;
    }

    public boolean isGoodDefect() {
        return goodDefect;
    }

    public void setGoodDefect(boolean goodDefect) {
        this.goodDefect = goodDefect;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID_Good;
    private String goodName;
    private  double goodPrice;
    private int goodQuantity;
    private Date goodSupplyDate;
    private boolean goodDefect;

}
