package org.thomasmore.oo3.course.resortui.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.thomasmore.oo3.course.resortui.business.entity.UserEntity;
import org.thomasmore.oo3.course.resortui.dao.UserDao;
import org.thomasmore.oo3.course.resortui.model.SessionDto;

@Named(value = "profile")
@RequestScoped
public class ProfileController {

    private String pageRedirect = "profile.xhtml??faces-redirect=true";
    private UserEntity ue;
    private List<UserEntity> users;

    @Inject
    private SessionDto sessionDto;
    @EJB
    private UserDao dao;

    @PostConstruct
    public void init() {
        users = dao.listAll();
        for (UserEntity user : users) {
            if (sessionDto.getUserDto().getId().equals(user.getId())) {
                ue = user;
            }
        }
    }

    public String edit() {
        ue.setEmail(sessionDto.getUserDto().getEmail());
        ue.setFullName(sessionDto.getUserDto().getFullName());
        ue.setPhone(sessionDto.getUserDto().getPhone());
        ue.setUsername(sessionDto.getUserDto().getUsername());
        dao.save(ue);
        return pageRedirect;
    }

    public SessionDto getSessionDto() {
        return sessionDto;
    }

    public void setSessionDto(SessionDto sessionDto) {
        this.sessionDto = sessionDto;
    }
}
