package org.thomasmore.oo3.course.resortui.controller;

import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.thomasmore.oo3.course.resortui.business.entity.UserEntity;
import org.thomasmore.oo3.course.resortui.dao.UserDao;
import org.thomasmore.oo3.course.resortui.model.RegistrationPageDto;

@Named(value = "registration")
@RequestScoped
public class RegistrationController {

    private RegistrationPageDto dto;
    private String pageRedirect = "index.xhtml??faces-redirect=true";

    @EJB
    private UserDao registrationDao;

    @PostConstruct
    public void init() {
        dto = new RegistrationPageDto();
    }

    public String add() {
        UserEntity ue = new UserEntity();
        dto.getDetail().setId(UUID.randomUUID().toString());
        ue.setId(dto.getDetail().getId());
        ue.setUsername(dto.getDetail().getUsername());
        ue.setPassword(dto.getDetail().getPassword());
        ue.setLevel(2);
        registrationDao.save(ue);
        return "login.xhtml";
    }

    public RegistrationPageDto getDto() {
        return dto;
    }

    public void setDto(RegistrationPageDto dto) {
        this.dto = dto;
    }
    
}
