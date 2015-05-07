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
 
import java.io.Serializable;
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
import org.thomasmore.oo3.course.resortui.model.SessionDto;

/**
 *
 * @author lucs
 */
@Named(value = "login")
@RequestScoped
public class LoginController implements Serializable {

    @Inject
    private SessionDto sessionDto;

    @EJB
    private UserDao userDao;
    private List<UserEntity> userentities;

    @PostConstruct
    public void init() {
        userentities = userDao.listAll();
    }

    public String goToLogin(){
        return "/login.xhtml?faces-redirect=true";
    }

    public String login() {
        String dbUserName;
        String dbPassword;

        for (UserEntity user : userentities) {
            dbUserName = user.getUsername();
            dbPassword = user.getPassword();
            
            if (sessionDto.getUserDto().getUsername().equals(dbUserName) && sessionDto.getUserDto().getPassword().equals(dbPassword)) {
                sessionDto.getUserDto().setId(user.getId());
                sessionDto.getUserDto().setFullName(user.getFullName());
                sessionDto.getUserDto().setEmail(user.getEmail());
                sessionDto.getUserDto().setUsername(dbUserName);
                sessionDto.getUserDto().setPassword(dbPassword);
                sessionDto.getUserDto().setLoggedIn(true);
                sessionDto.getUserDto().setLevel(user.getLevel());
                return "profile.xhtml";
            }      
        }
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage("Foute gebruikersnaam en/of wachtwoord, gelieve opnieuw te proberen.");
        facesContext.addMessage(null, facesMessage);
        return null;
    }
    
     public String logOut() {
                 
        // Zet waarde van loggedIn op false
        sessionDto.getUserDto().setLoggedIn(false);
        
        // Breek sessie af
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        // Schrijf bericht dat uitloggen gelukt is
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage("U bent uitgelogd.");
        facesContext.addMessage(null, facesMessage);
        
        // Redirect 
        return "login.xhtml?faces-redirect=true";   
    }
    
    public SessionDto getSessionDto() {
        return sessionDto;
    }

    public void setSessionDto(SessionDto sessionDto) {
        this.sessionDto = sessionDto;
    }
}
