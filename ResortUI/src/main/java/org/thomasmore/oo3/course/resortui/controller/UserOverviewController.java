
package org.thomasmore.oo3.course.resortui.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.thomasmore.oo3.course.resortui.facade.UserOverviewFacade;
import org.thomasmore.oo3.course.resortui.model.UserOverviewPageDto;

@Named(value = "useroverview")
@RequestScoped
public class UserOverviewController {
    
    private UserOverviewPageDto dto;
    private final String pageRedirect = "useroverview.xhtml?faces-redirect=true";
    
    @EJB
    private UserOverviewFacade facade;
    
    @PostConstruct
    public void init() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String editId = req.getParameter("edit");
        String deleteId = req.getParameter("delete");
        dto = facade.loadUserOverviewPage(editId, deleteId);
    } 

    public UserOverviewPageDto getDto() {
        return dto;
    }

    public void setDto(UserOverviewPageDto dto) {
        this.dto = dto;
    }
    
}