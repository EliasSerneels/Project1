/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.thomasmore.oo3.course.resortui.business.entity.EventCompanyEntity;
import org.thomasmore.oo3.course.resortui.facade.EventCompanyFacade;
import org.thomasmore.oo3.course.resortui.model.EventCompanyPageDto;

/**
 *
 * @author Jeroen
 */
@Named(value = "eventcompany")
@RequestScoped
public class EventCompanyController {
    
    private EventCompanyPageDto dto;
    private String pageRedirect="eventcompany.xhtml?faces-redirect=true";
    private List<EventCompanyEntity> selectedEventCompany;


    @EJB
    private EventCompanyFacade eventcompanyFacade;

    @PostConstruct
    public void init() {

        dto = new EventCompanyPageDto();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String editId = req.getParameter("edit");
        String deleteId = req.getParameter("delete");
        dto = eventcompanyFacade.loadEventCompanyOverviewPage(editId, deleteId);
        
     
        
    }
    
    public String add() {
        
        eventcompanyFacade.add(dto);
        
        return pageRedirect;
    }
    
    public EventCompanyPageDto getDto() {
        return dto;
    }

    public void setDto(EventCompanyPageDto dto) {
        this.dto = dto;
    }
    
         public List<EventCompanyEntity> getSelectedEventCompany() {
        return selectedEventCompany;
    }

    public void setSelectedEventCompany(List<EventCompanyEntity> selectedEventCompany) {
        this.selectedEventCompany = selectedEventCompany;
    }  
}
