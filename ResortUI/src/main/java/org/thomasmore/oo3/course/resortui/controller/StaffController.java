/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.thomasmore.oo3.course.resortui.facade.StaffFacade;
import org.thomasmore.oo3.course.resortui.model.StaffPageDto;

/**
 *
 * @author Jeroen
 */
public class StaffController {
private StaffPageDto dto;
    private String pageRedirect="staff.xhtml??faces-redirect=true";
    
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

    public StaffPageDto getDto() {
        return dto;
    }

    public void setDto(StaffPageDto dto) {
        this.dto = dto;
    }
    
    }
    

