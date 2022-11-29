package com.example.springmodels.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;

@Entity
public class modelGood {

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

    public modelEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(modelEmployee employee) {
        this.employee = employee;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID_Good;
    @NotBlank(message = "Поле не может быть пустым")
    @NotNull(message = "не может быть нулевым")
    private String goodName;
    @NotNull(message = "не может быть нулевым")
    @Positive(message = "Цена не может быть отрицательной")
    @Digits(fraction = 2, integer = 5, message = "Количество символов слева от запятой не может превышать 5, справа - 2")
    private  double goodPrice;
    @Max(value = 10000, message = "Количество не может быть больше 10000")
    @PositiveOrZero(message = "Количество не может быть меньше нуля")
    private int goodQuantity;
    @NotNull(message = "не может быть нулевым")
    //@PastOrPresent(message = "Дата должна быть раньше или равна текущей")
    private Date goodSupplyDate;
    private boolean goodDefect;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private modelEmployee employee;

    public modelGood(Long ID_Good, String goodName, double goodPrice, int goodQuantity, Date goodSupplyDate, boolean goodDefect, modelEmployee employee) {
        this.ID_Good = ID_Good;
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.goodQuantity = goodQuantity;
        this.goodSupplyDate = goodSupplyDate;
        this.goodDefect = goodDefect;
        this.employee = employee;

    }
}
