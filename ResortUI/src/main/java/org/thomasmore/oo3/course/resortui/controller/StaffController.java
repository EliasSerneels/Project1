/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.thomasmore.oo3.course.resortui.business.entity.StaffEntity;
import org.thomasmore.oo3.course.resortui.facade.StaffFacade;
import org.thomasmore.oo3.course.resortui.model.StaffPageDto;

/**
 *
 * @author Jeroen
 */
@Named(value = "staff")
@RequestScoped
public class StaffController implements Serializable {

    private StaffPageDto dto;
    private final String pageRedirect = "staff.xhtml?faces-redirect=true";
    private final String pageEdit="event.xhtml?edit=${listDetail.id}";
    private List<StaffEntity> selectedStaff;

    @EJB
    private StaffFacade staffFacade;

    @PostConstruct
      public void init() {

        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String editId = req.getParameter("edit");
        String deleteId = req.getParameter("delete");

        dto = staffFacade.loadStaffOverviewPage(editId, deleteId);
    }

    public String add() {
        staffFacade.add(dto);
        return pageRedirect;
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
    
    public String getPageEdit() {
        return pageEdit;
    }  
}