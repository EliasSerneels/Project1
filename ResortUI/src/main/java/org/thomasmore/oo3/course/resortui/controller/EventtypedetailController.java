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
import org.thomasmore.oo3.course.resortui.facade.EventTypeDetailFacade;
import org.thomasmore.oo3.course.resortui.model.EventtypedetailPageDto;

/**
 *
 * @author Jeroen
 */
@Named(value = "eventtypedetail")
@RequestScoped
public class EventTypeDetailController {




    private EventtypedetailPageDto dto;
    private String pageRedirect="eventtypedetail.xhtml?faces-redirect=true";

    @EJB
    private EventTypeDetailFacade eventtypedetailFacade;

    @PostConstruct
    public void init() {

        dto = new EventtypedetailPageDto();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String editId = req.getParameter("edit");
        String deleteId = req.getParameter("delete");
        dto = eventtypedetailFacade.loadEventtypedetailOverviewPage(editId, deleteId);
        
    }

    public String add() {
        
        eventtypedetailFacade.add(dto);
        
        return pageRedirect;
    }
    
    public EventtypedetailPageDto getDto() {
        return dto;
    }

    public void setDto(EventtypedetailPageDto dto) {
        this.dto = dto;
    }
}
