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

import org.thomasmore.oo3.course.resortui.business.entity.EventTypeEntity;
import org.thomasmore.oo3.course.resortui.facade.EventTypeFacade;
import org.thomasmore.oo3.course.resortui.model.EventTypePageDto;

/**
 *
 * @author Jeroen
 */
@Named(value = "eventtype")
@RequestScoped
public class EventTypeController {

    private EventTypePageDto dto;
    private final String pageRedirect="eventtype.xhtml?faces-redirect=true";
    private List<EventTypeEntity> selectedEventtype;


    @EJB
    private EventTypeFacade eventtypeFacade;

    @PostConstruct
    public void init() {

        dto = new EventTypePageDto();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String editId = req.getParameter("edit");
        String deleteId = req.getParameter("delete");
        dto = eventtypeFacade.loadEventtypeOverviewPage(editId, deleteId);
        
    }

    public String add() {
        
        eventtypeFacade.add(dto);
        
        return pageRedirect;
    }
    
    public EventTypePageDto getDto() {
        return dto;
    }

    public void setDto(EventTypePageDto dto) {
        this.dto = dto;
    }
    
      public List<EventTypeEntity> getSelectedEventtype() {
        return selectedEventtype;
    }

    public void setSelectedEventtype(List<EventTypeEntity> selectedEventtype) {
        this.selectedEventtype = selectedEventtype;
    }   

}

