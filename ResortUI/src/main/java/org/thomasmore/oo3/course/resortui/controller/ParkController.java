/*
 * Copyright (C) 2014 lucs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.thomasmore.oo3.course.resortui.business.entity.CustomerEntity;

import org.thomasmore.oo3.course.resortui.business.entity.ParkEntity;
import org.thomasmore.oo3.course.resortui.facade.ParkFacade;
import org.thomasmore.oo3.course.resortui.model.ParkPageDto;

/**
 *
 * @author lucs
 */
@Named(value = "park")
@RequestScoped
public class ParkController {

    private ParkPageDto dto;
    private String pageRedirect="park.xhtml??faces-redirect=true";
    private List<ParkEntity> selectedParks;

    @EJB
    private ParkFacade parkFacade;
    @PostConstruct
    public void init() {

        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String editId = req.getParameter("edit");
        String deleteId = req.getParameter("delete");
        dto = parkFacade.loadParkOverviewPage(editId, deleteId);
        
    }

    public String add() {
        
        parkFacade.add(dto);
        
        return pageRedirect;
    }
    
       public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Customer Selected", ((CustomerEntity) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Customer Unselected", ((CustomerEntity) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public ParkPageDto getDto() {
        return dto;
    }

    public void setDto(ParkPageDto dto) {
        this.dto = dto;
    }

    public List<ParkEntity> getSelectedParks() {
        return selectedParks;
    }

    public void setSelectedParks(List<ParkEntity> selectedParks) {
        this.selectedParks = selectedParks;
    }

    
}
