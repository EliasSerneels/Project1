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
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.thomasmore.oo3.course.resortui.business.entity.StaffEntity;
import org.thomasmore.oo3.course.resortui.facade.StaffFacade;
import org.thomasmore.oo3.course.resortui.model.StaffPageDto;

/**
 *
 * @author Jeroen
 */
@Named(value = "staff")
@RequestScoped

public class StaffController {
    private StaffPageDto dto;
    private String pageRedirect="staff.xhtml??faces-redirect=true";
    private List<StaffEntity> selectedStaff;
    
    @EJB
    private StaffFacade staffFacade;
    
    @PostConstruct
    public void init() {
        
        dto = new StaffPageDto();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String editId = req.getParameter("edit");
        String deleteId = req.getParameter("delete");
        dto = staffFacade.loadStaffOverviewPage(editId, deleteId);
    }

    public String add(){
        staffFacade.add(dto);
        return pageRedirect;
        
        }
    
      public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Staff Selected", ((StaffEntity) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Staff Unselected", ((StaffEntity) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public StaffPageDto getDto() {
        return dto;
    }

    public void setDto(StaffPageDto dto) {
        this.dto = dto;
    }

    public List<StaffEntity> getSelectedStaff() {
        return selectedStaff;
    }

    public void setSelectedStaff(List<StaffEntity> selectedStaff) {
        this.selectedStaff = selectedStaff;
    }
    
    
    
    }
