
package org.thomasmore.oo3.course.resortui.controller;

/**
 *
 * @author Moegie
 */

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.thomasmore.oo3.course.resortui.model.SessionDto;

@Named(value="profile")
@RequestScoped
public class ProfileController {
    private String pageRedirect="profile.xhtml??faces-redirect=true";
    
    @Inject
    private SessionDto sessionDto;
    
    @PostConstruct
    public void init(){
    }
    
    public String editUsername() {
        sessionDto.getUserDto().setUsername("jos");
        return pageRedirect;
    }

    public SessionDto getSessionDto() {
        return sessionDto;
    }

    public void setSessionDto(SessionDto sessionDto) {
        this.sessionDto = sessionDto;
    }   
}