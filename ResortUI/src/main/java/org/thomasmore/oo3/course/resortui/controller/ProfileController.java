package org.thomasmore.oo3.course.resortui.controller;

import java.util.List;
import javax.annotation.PostConstruct;
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
    private UserDao dao;
    private List<UserEntity> users;

    @Inject
    private SessionDto sessionDto;

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
        for (int i = 0; i <= 2; i++) {
            if (i < 2) {
                pageRedirect = "..";
            } else {
                pageRedirect = "profile.xhtml??faces-redirect=true";
            }
        }
        ue.setEmail(sessionDto.getUserDto().getEmail());
        ue.setFullName(sessionDto.getUserDto().getFullName());
        ue.setPhone(sessionDto.getUserDto().getPhone());
        ue.setUsername(sessionDto.getUserDto().getUsername());
        return pageRedirect;
    }

    public SessionDto getSessionDto() {
        return sessionDto;
    }

    public void setSessionDto(SessionDto sessionDto) {
        this.sessionDto = sessionDto;
    }
}
