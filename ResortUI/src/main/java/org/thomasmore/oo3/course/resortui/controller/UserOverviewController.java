package org.thomasmore.oo3.course.resortui.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.thomasmore.oo3.course.resortui.business.entity.UserEntity;
import org.thomasmore.oo3.course.resortui.facade.UserOverviewFacade;
import org.thomasmore.oo3.course.resortui.model.UserOverviewPageDto;

@Named(value = "useroverview")
@RequestScoped
public class UserOverviewController {

    private UserOverviewPageDto dto;
    private final String pageRedirect = "useroverview.xhtml?faces-redirect=true";
    private List<UserEntity> selectedUser;

    @EJB
    private UserOverviewFacade facade;

    @PostConstruct
    public void init() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String editId = req.getParameter("edit");
        String deleteId = req.getParameter("delete");
        dto = facade.loadUserOverviewPage(editId, deleteId);
    }

    public String add() {
        facade.add(dto);
        return pageRedirect;
    }

    public UserOverviewPageDto getDto() {
        return dto;
    }

    public void setDto(UserOverviewPageDto dto) {
        this.dto = dto;
    }

    public List<UserEntity> getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(List<UserEntity> selectedUser) {
        this.selectedUser = selectedUser;
    }
}
