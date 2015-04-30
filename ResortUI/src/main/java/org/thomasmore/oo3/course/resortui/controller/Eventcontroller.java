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
import org.thomasmore.oo3.course.resortui.business.entity.EventEntity;
import org.thomasmore.oo3.course.resortui.facade.EventFacade;
import org.thomasmore.oo3.course.resortui.model.EventPageDto;

/**
 *
 * @author Jeroen
 */
@Named(value = "event")
@RequestScoped
public class Eventcontroller {

    private EventPageDto dto;
    private String pageRedirect="event.xhtml?faces-redirect=true";
    private List<EventEntity> selectedEvent;

    @EJB
    private EventFacade eventFacade;
    
    @PostConstruct
    public void init() {

        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String editId = req.getParameter("edit");
        String deleteId = req.getParameter("delete");
        dto = eventFacade.loadEventOverviewPage(editId, deleteId);
        
    }
    
    public String add() {
        
        eventFacade.add(dto);
        
        return pageRedirect;
    }
    
    public EventPageDto getDto() {
        return dto;
    }

    public void setDto(EventPageDto dto) {
        this.dto = dto;
    }

    public List<EventEntity> getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(List<EventEntity> selectedEvent) {
        this.selectedEvent = selectedEvent;
    }
    
}
