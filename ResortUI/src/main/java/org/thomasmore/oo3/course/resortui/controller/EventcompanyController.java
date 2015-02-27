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
import org.thomasmore.oo3.course.resortui.facade.EventcompanyFacade;
import org.thomasmore.oo3.course.resortui.model.EventcompanyPageDto;

/**
 *
 * @author Jeroen
 */
@Named(value = "eventcompany")
@RequestScoped
public class EventcompanyController {
    
    private EventcompanyPageDto dto;
    private String pageRedirect="eventcompany.xhtml?faces-redirect=true";

    @EJB
    private EventcompanyFacade eventcompanyFacade;

    @PostConstruct
    public void init() {

        dto = new EventcompanyPageDto();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String editId = req.getParameter("edit");
        String deleteId = req.getParameter("delete");
        dto = eventcompanyFacade.loadEventcompanyOverviewPage(editId, deleteId);
        
    }
    
    public String add() {
        
        eventcompanyFacade.add(dto);
        
        return pageRedirect;
    }
    
    public EventcompanyPageDto getDto() {
        return dto;
    }

    public void setDto(EventcompanyPageDto dto) {
        this.dto = dto;
    }
}
