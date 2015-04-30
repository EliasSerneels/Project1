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
import org.thomasmore.oo3.course.resortui.facade.LocationFacade;
import org.thomasmore.oo3.course.resortui.model.LocationPageDto;

/**
 *
 * @author Elliot
 */
@Named(value = "location")
@RequestScoped

public class LocationController {

    private LocationPageDto dto;
    private String pageRedirect = "location.xhtml??faces-redirect=true";

    @EJB
    private LocationFacade locationFacade;

    @PostConstruct
    public void init() {

        dto = new LocationPageDto();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String editId = req.getParameter("edit");
        String deleteId = req.getParameter("delete");
        dto = locationFacade.loadLocationOverviewPage(editId, deleteId);
    }

    public String add() {
        locationFacade.add(dto);
        return pageRedirect;

    }

    public LocationPageDto getDto() {
        return dto;
    }

    public void setDto(LocationPageDto dto) {
        this.dto = dto;
    }
}
