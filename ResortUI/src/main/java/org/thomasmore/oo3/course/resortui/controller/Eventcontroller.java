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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.thomasmore.oo3.course.resortui.business.entity.EventEntity;
import org.thomasmore.oo3.course.resortui.facade.EventFacade;
import org.thomasmore.oo3.course.resortui.model.EventPageDto;

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
        
        
        
        // Voorlopige oplossing
        eventFacade.add(dto);
           
        // Werkt nog niet
        if(eventFacade.isStartAfterEnd()){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("De startdatum/tijd komt na de gekozen einddatum/tijd.");
            facesContext.addMessage(null, facesMessage);
            
        }else if(eventFacade.isDoubleBooking()){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("De gekozen bungalow is die datum reeds bezet. Gelieve een andere datum te selecteren.");
            facesContext.addMessage(null, facesMessage);
            
        }   

        
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
