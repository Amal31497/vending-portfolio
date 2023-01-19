package com.company.vendingmachineportfolio.dao;

import com.company.vendingmachineportfolio.model.SalesLead;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class SalesLeadDAOImpl {
    SalesLeadDAO salesLeadDAO = new SalesLeadDAO();
    CommunicationLogDAO communicationLogDAO = new CommunicationLogDAO();

    public void createLeadImpl(SalesLead salesLead) throws Exception {
        try {
            salesLeadDAO.createLead(salesLead);
        } catch(Exception ex) {
            throw new SQLException("Error at LeadDAOImpl.createLeadImpl()");
        }
    }

    public ArrayList<SalesLead> getAllLeadsImpl() throws Exception {
        SalesLeadDAO salesLeadDAO = new SalesLeadDAO();
        ArrayList<SalesLead> result = new ArrayList<>();
        try {
            result = salesLeadDAO.getAllLeads();
        } catch (Exception ex){
            throw new SQLException("Error at SalesLeadDAOImpl.getAllLeadsImpl()");
        }
        return result;
    }

    public SalesLead getLeadImpl(String leadId) throws Exception {
        SalesLead result = new SalesLead();
        try {
            result = salesLeadDAO.getLead(leadId);
        } catch(Exception ex) {
            throw new SQLException("Error at LeadDAOImpl.getLeadImpl()");
        }
        return result;
    }

    public void updateLeadImpl(SalesLead salesLead) throws Exception {
        try {
            salesLeadDAO.updateLead(salesLead);
        } catch (Exception ex){
            throw new SQLException("Error at LeadDAOImpl.updateLeadImpl()");
        }
    }

    public void deleteLeadImpl(String leadId) throws Exception {
        try {
            SalesLead salesLead = salesLeadDAO.getLead(leadId);
            salesLeadDAO.deleteLead(salesLead.getLeadId());
            try {
                communicationLogDAO.deleteLogsWithLeadId(salesLead.getLeadId());
            } catch (Exception ex){
                salesLeadDAO.createLead(salesLead);
                throw new SQLException("Error at LeadDAOImpl.deleteLeadImpl() could not delete communication logs for this lead, conducting rollback");
            }
        } catch (Exception ex){
            throw new SQLException("Error at LeadDAOImpl.deleteLeadImpl()");
        }
    }
}
