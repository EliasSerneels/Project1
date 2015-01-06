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
import javax.inject.Inject;
import javax.inject.Named;
import org.thomasmore.oo3.course.resortui.business.entity.UserEntity;
import org.thomasmore.oo3.course.resortui.dao.UserDao;
import org.thomasmore.oo3.course.resortui.model.LoginDto;
import org.thomasmore.oo3.course.resortui.model.SessionDto;

/**
 *
 * @author lucs
 */
@Named(value = "login")
@RequestScoped
public class LoginController {

    private LoginDto dto;

    @Inject
    private SessionDto sessionDto;
    
    @EJB
    private UserDao userDao;
    private List<UserEntity> userentities;
    @PostConstruct
    public void init() {
        dto = new LoginDto();
        userentities = userDao.listAll();
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }
    
    public String login() {
        String test = "nee";
        for (UserEntity user : userentities ) {
           

            if (dto.getName().equals(user.getUsername()) && dto.getPassword().equals(user.getPassword())) {
                test = "ja";
            }
        }
        
        String naam ="pieter";
        String pwd ="stinkt";
        
        if (!dto.getName().equals(naam) && !dto.getPassword().equals(pwd)) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("U moet ingelogd zijn om deze pagina te kunnen bezoeken. "+test);
            facesContext.addMessage(null, facesMessage);
            return null;
        }
        sessionDto.getUserDto().setPassword(pwd);
        sessionDto.getUserDto().setUsername(naam);
        sessionDto.getUserDto().setLoggedIn(true);
        return "index.xhtml";
    }

    public LoginDto getDto() {
        return dto;
    }

    public void setDto(LoginDto dto) {
        this.dto = dto;
    }

}
