/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.ResizeEvent;

/**
 *
 * @author Elias Serneels
 */
public class OverviewController {
    
    public void onResize(ResizeEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Image resized", "Width:" + event.getWidth() + ",Height:" + event.getHeight());
 
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
