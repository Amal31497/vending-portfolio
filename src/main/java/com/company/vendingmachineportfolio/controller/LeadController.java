package com.company.vendingmachineportfolio.controller;

import com.company.vendingmachineportfolio.dao.SalesLeadDAOImpl;
import com.company.vendingmachineportfolio.model.SalesLead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class LeadController {

    @Autowired
    SalesLeadDAOImpl salesLeadDAOImpl;

    @GetMapping("/api/lead/all")
    @ResponseStatus(value = HttpStatus.OK)
    public List<SalesLead> getAllLeads() throws Exception {
        List<SalesLead> salesLeadList = salesLeadDAOImpl.getAllLeadsImpl();
        return salesLeadList;
    }

    @GetMapping("/api/lead/get/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public SalesLead getLead(@PathVariable String id) throws Exception {
        SalesLead salesLead = salesLeadDAOImpl.getLeadImpl(id);
        return salesLead;
    }

    @PostMapping("/api/lead/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createNewLead(@RequestBody @Valid SalesLead salesLead) throws Exception {
        salesLeadDAOImpl.createLeadImpl(salesLead);
    }

    @PutMapping("/api/lead/updateLead")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateLead(@RequestBody @Valid SalesLead salesLead) throws Exception {
        salesLeadDAOImpl.updateLeadImpl(salesLead);
    }

    @DeleteMapping("/api/lead/delete/{leadId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteLead(@PathVariable String leadId) throws Exception {
        salesLeadDAOImpl.deleteLeadImpl(leadId);
    }
}
