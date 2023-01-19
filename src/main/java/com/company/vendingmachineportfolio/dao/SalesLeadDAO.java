package com.company.vendingmachineportfolio.dao;

import com.company.vendingmachineportfolio.model.SalesLead;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;


public class SalesLeadDAO extends CommonDAO {
    private SalesLead parse(ResultSet rs) throws SQLException {
        SalesLead salesLead = new SalesLead();
        salesLead.setLeadId(rs.getString("lead_id"));
        salesLead.setName(rs.getString("name"));
        salesLead.setEmail(rs.getString("email"));
        salesLead.setPhone(rs.getString("phone"));
        salesLead.setAddress(rs.getString("address"));
        salesLead.setStatus(rs.getString("status"));
        salesLead.setFirstAdded(rs.getTimestamp("first_added").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        salesLead.setPartneredOrNot(rs.getBoolean("partnered_or_not"));
        salesLead.setTotalIncome(rs.getDouble("total_income"));
        salesLead.setAverageMonthly(rs.getDouble("average_monthly"));
        salesLead.setAverageDaily(rs.getDouble("average_daily"));
        return salesLead;
    }

    public void createLead(SalesLead salesLead) throws Exception {
        String query = "INSERT INTO vending_crm.sales_lead " +
                "(lead_id,name,email,phone,address,status,first_added) " +
                "values(?,?,?,?,?,?,now());";
        PreparedStatement stmt;
        Connection con = this.getConnection();
        stmt = con.prepareStatement(query);
        try {
            stmt.setString(1, salesLead.getLeadId());
            stmt.setString(2, salesLead.getName());
            stmt.setString(3, salesLead.getEmail());
            stmt.setString(4, salesLead.getPhone());
            stmt.setString(5, salesLead.getAddress());
            stmt.setString(6, salesLead.getStatus());
            stmt.execute();
        } catch (Exception ex){
            throw new SQLException("Error while creating a new lead : " + ex);
        } finally {
            cleanUp(con, null, stmt);
        }
    }

    public ArrayList<SalesLead> getAllLeads() throws Exception {
        String query = "SELECT * FROM sales_lead;";
        PreparedStatement stmt;
        Connection con = this.getConnection();
        ResultSet rs = null;
        stmt = con.prepareStatement(query);
        ArrayList<SalesLead> result = new ArrayList<>();

        try {
            rs = stmt.executeQuery();
            while(rs.next()){
                result.add(parse(rs));
            }
        } catch (Exception ex) {
            throw new SQLException("Error at getAllLeads " + ex);
        } finally {
            cleanUp(con, rs, stmt);
        }

        return result;
    }

    public SalesLead getLead(String leadId) throws Exception {
        String query = "SELECT * FROM vending_crm.sales_lead WHERE lead_id=?;";
        PreparedStatement stmt = null;
        Connection con = this.getConnection();
        ResultSet rs = null;
        SalesLead result = new SalesLead();

        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, leadId);
            rs = stmt.executeQuery();
            if(rs.next()) result = this.parse(rs);
        } catch (Exception ex){
            throw new SQLException("Error at getLead() " + ex);
        } finally {
            cleanUp(con,rs,stmt);
        }

        return result;
    }

    public void updateLead(SalesLead salesLead) throws Exception {
        SalesLead oldVersion;
        try {
            oldVersion = this.getLead(salesLead.getLeadId());
        } catch (Exception ex) {
            throw new SQLException("Error at DAO.UpdateLead : no lead with that id was found");
        }

        if(oldVersion == null) return;

        String query = "UPDATE sales_lead " +
                "SET name=?,email=?,phone=?,address=?," +
                "status=?,partnered_or_not=?," +
                "total_income=?,average_monthly=?,average_daily=? WHERE lead_id=?;";

        PreparedStatement stmt;
        Connection con = this.getConnection();
        stmt = con.prepareStatement(query);

        try {
            stmt.setString(1, salesLead.getName() == null ? oldVersion.getName() : salesLead.getName());
            stmt.setString(2, salesLead.getEmail() == null ? oldVersion.getEmail() : salesLead.getEmail());
            stmt.setString(3, salesLead.getPhone() == null ? oldVersion.getPhone() : salesLead.getPhone());
            stmt.setString(4, salesLead.getAddress() == null ? oldVersion.getAddress() : salesLead.getAddress());
            stmt.setString(5, salesLead.getStatus() == null ? oldVersion.getStatus() : salesLead.getStatus());
            stmt.setBoolean(6, salesLead.getPartneredOrNot());
            stmt.setDouble(7, salesLead.getTotalIncome());
            if(salesLead.getTotalIncome() > 0){
                LocalDate now = new Timestamp(System.currentTimeMillis()).toLocalDateTime().toLocalDate();
                LocalDate curr = salesLead.getCurrentContractEnd();
                int months = Period.between(now,curr).getMonths();
                int days = Period.between(now,curr).getDays();
                stmt.setDouble(8, salesLead.getTotalIncome()/months);
                stmt.setDouble(9, salesLead.getTotalIncome()/days);
            } else {
                stmt.setDouble(8, oldVersion.getAverageMonthly());
                stmt.setDouble(9, oldVersion.getAverageDaily());
            }
            stmt.setString(10, salesLead.getLeadId());
            stmt.execute();
        } catch (Exception ex) {
            throw new SQLException("Error at updateLead() : " + ex);
        } finally {
            cleanUp(con,null,stmt);
        }
    }

    public void deleteLead(String leadId) throws Exception {
        String query = "DELETE FROM sales_lead WHERE lead_id=?;";
        PreparedStatement stmt;
        Connection con = this.getConnection();
        stmt = con.prepareStatement(query);

        try {
            stmt.setString(1, leadId);
            stmt.execute();
        } catch (Exception ex){
            throw new SQLException("Error at deleteLead() : " + ex);
        } finally {
            cleanUp(con, null, stmt);
        }
    }
}
