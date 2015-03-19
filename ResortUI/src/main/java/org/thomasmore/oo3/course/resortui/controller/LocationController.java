/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.thomasmore.oo3.course.resortui.model.LocationPageDto;

/**
 *
 * @author Jeroen
 */


    @Named(value = "location")
@RequestScoped

public class LocationController {
    private LocationPageDto dto;
    private String pageRedirect="location.xhtml??faces-redirect=true";
    
    @EJB
    private LocationFacade customerFacade;
    
    @PostConstruct
    public void init() {
        
        dto = new LocationPageDto();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String editId = req.getParameter("edit");
        String deleteId = req.getParameter("delete");
        dto = customerFacade.loadCustomerOverviewPage(editId, deleteId);
    }

    public String add(){
        customerFacade.add(dto);
        return pageRedirect;
        
        }

    public LocationPageDto getDto() {
        return dto;
    }

    public void setDto(LocationPageDto dto) {
        this.dto = dto;
    }
}
