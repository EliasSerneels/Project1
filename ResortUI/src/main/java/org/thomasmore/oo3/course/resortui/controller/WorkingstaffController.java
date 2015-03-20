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
import org.thomasmore.oo3.course.resortui.facade.WorkingstaffFacade;
import org.thomasmore.oo3.course.resortui.model.WorkingstaffPageDto;

/**
 *
 * @author Jeroen
 */
   
@Named(value = "workingstaff")
@RequestScoped
public class WorkingstaffController {

    private WorkingstaffPageDto dto;
    private String pageRedirect="workingstaff.xhtml?faces-redirect=true";

    @EJB
    private WorkingstaffFacade workingstaffFacade;

    @PostConstruct
    public void init() {

        dto = new WorkingstaffPageDto();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String editId = req.getParameter("edit");
        String deleteId = req.getParameter("delete");
        dto = workingstaffFacade.loadWorkingstaffOverviewPage(editId, deleteId);
        
    }

    public String add() {
        
        workingstaffFacade.add(dto);
        
        return pageRedirect;
    }
    
    public WorkingstaffPageDto getDto() {
        return dto;
    }

    public void setDto(WorkingstaffPageDto dto) {
        this.dto = dto;
    }

}
