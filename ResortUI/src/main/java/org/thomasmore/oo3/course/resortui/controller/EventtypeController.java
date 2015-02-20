/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.thomasmore.oo3.course.resortui.facade.EventtypeFacade;
import org.thomasmore.oo3.course.resortui.model.EventtypePageDto;

/**
 *
 * @author Jeroen
 */
@Named(value = "eventtype")
@RequestScoped
public class EventtypeController {

    private EventtypePageDto dto;
    private String pageRedirect="eventtype.xhtml?faces-redirect=true";

    @EJB
    private EventtypeFacade eventtypeFacade;

    @PostConstruct
    public void init() {

        dto = new EventtypePageDto();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String editId = req.getParameter("edit");
        String deleteId = req.getParameter("delete");
        dto = eventtypeFacade.loadEventtypeOverviewPage(editId, deleteId);
        
    }

    public String add() {
        
        dto = eventtypeFacade.add();
        
        return pageRedirect;
    }
    
    public EventtypePageDto getDto() {
        return dto;
    }

    public void setDto(EventtypePageDto dto) {
        this.dto = dto;
    }

}

