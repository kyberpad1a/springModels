package com.example.springmodels.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class modelPasport {
    public modelPasport(){}
    @Id
    @GeneratedValue
    private Long ID_Pasport;
    private int pasportNumber;
    private int pasportSeries;
    @OneToOne(optional = true, mappedBy = "employeePasport")
    private modelEmployee owner;


    public Long getID_Pasport() {
        return ID_Pasport;
    }

    public void setID_Pasport(Long ID_Pasport) {
        this.ID_Pasport = ID_Pasport;
    }

    public int getPasportNumber() {
        return pasportNumber;
    }

    public void setPasportNumber(int pasportNumber) {
        this.pasportNumber = pasportNumber;
    }

    public int getPasportSeries() {
        return pasportSeries;
    }

    public void setPasportSeries(int pasportSeries) {
        this.pasportSeries = pasportSeries;
    }

    public modelEmployee getOwner() {
        return owner;
    }

    public void setOwner(modelEmployee owner) {
        this.owner = owner;
    }
}
