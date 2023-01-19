package com.company.vendingmachineportfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "sales_lead")
public class SalesLead implements Serializable {
    @Id
    @Column(name="lead_id")
    private String leadId;
    @Column(name="name")
    private String name;
    @Column(unique = true, name = "email")
    private String email;
    @Column(name="phone")
    private String phone;
    @Column(name="address")
    private String address;
    @Column(name="status")
    private String status;
    @Column(name="firstAdded")
    private LocalDate firstAdded;
    @Column(name="currentContractEnd")
    private LocalDate currentContractEnd;
    @Column(name="partneredOrNot")
    private Boolean partneredOrNot;
    @Column(name="totalIncome")
    private Double totalIncome = 0.0;
    @Column(name="averageMonthly")
    private Double averageMonthly = 0.0;
    @Column(name="averageDaily")
    private Double averageDaily = 0.0;

    public SalesLead(){};

    public SalesLead(String leadId, String name, String email, String phone, String address, String status, LocalDate firstAdded, LocalDate currentContractEnd, Boolean partneredOrNot, Double totalIncome, Double averageMonthly, Double averageDaily) {
        this.leadId = leadId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.firstAdded = firstAdded;
        this.currentContractEnd = currentContractEnd;
        this.partneredOrNot = partneredOrNot;
        this.totalIncome = totalIncome;
        this.averageMonthly = averageMonthly;
        this.averageDaily = averageDaily;
    }

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getFirstAdded() {
        return firstAdded;
    }

    public void setFirstAdded(LocalDate firstAdded) {
        this.firstAdded = firstAdded;
    }

    public LocalDate getCurrentContractEnd() {
        return currentContractEnd;
    }

    public void setCurrentContractEnd(LocalDate currentContractEnd) {
        this.currentContractEnd = currentContractEnd;
    }

    public Boolean getPartneredOrNot() {
        return partneredOrNot;
    }

    public void setPartneredOrNot(Boolean partneredOrNot) {
        this.partneredOrNot = partneredOrNot;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getAverageMonthly() {
        return averageMonthly;
    }

    public void setAverageMonthly(Double averageMonthly) {
        this.averageMonthly = averageMonthly;
    }

    public Double getAverageDaily() {
        return averageDaily;
    }

    public void setAverageDaily(Double averageDaily) {
        this.averageDaily = averageDaily;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesLead salesLead = (SalesLead) o;
        return Objects.equals(leadId, salesLead.leadId) && Objects.equals(name, salesLead.name) && Objects.equals(email, salesLead.email) && Objects.equals(phone, salesLead.phone) && Objects.equals(address, salesLead.address) && Objects.equals(status, salesLead.status) && Objects.equals(firstAdded, salesLead.firstAdded) && Objects.equals(currentContractEnd, salesLead.currentContractEnd) && Objects.equals(partneredOrNot, salesLead.partneredOrNot) && Objects.equals(totalIncome, salesLead.totalIncome) && Objects.equals(averageMonthly, salesLead.averageMonthly) && Objects.equals(averageDaily, salesLead.averageDaily);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leadId, name, email, phone, address, status, firstAdded, currentContractEnd, partneredOrNot, totalIncome, averageMonthly, averageDaily);
    }

    @Override
    public String toString() {
        return "SalesLead{" +
                "leadId='" + leadId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", firstAdded=" + firstAdded +
                ", currentContractEnd=" + currentContractEnd +
                ", partneredOrNot=" + partneredOrNot +
                ", totalIncome=" + totalIncome +
                ", averageMonthly=" + averageMonthly +
                ", averageDaily=" + averageDaily +
                '}';
    }
}
