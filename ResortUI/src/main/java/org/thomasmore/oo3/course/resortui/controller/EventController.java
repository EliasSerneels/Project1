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
public class EventController {

    private EventPageDto dto;
    private String pageEdit="event.xhtml?edit=${listDetail.id}";
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
        
        String check = eventFacade.checkBooking(dto);
        switch (check) {
            case "begindateAfterEnddate":
            {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("De gekozen begindatum is later dan de gekozen einddatum.");
                facesContext.addMessage(null, facesMessage);
                return null;
            }
            case "doubleBooking":
                {
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    FacesMessage facesMessage = new FacesMessage("De gekozen locatie is reeds bezet voor deze datum. Gelieve een andere datum te kiezen.");
                    facesContext.addMessage(null, facesMessage);
                    return null;
                }
            default:
                return "event.xhtml?faces-redirect=true";
        }
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

    public String getPageEdit() {
        return pageEdit;
    }    
    
}
