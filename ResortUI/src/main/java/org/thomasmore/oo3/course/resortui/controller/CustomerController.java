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
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.thomasmore.oo3.course.resortui.business.entity.CustomerEntity;

import org.thomasmore.oo3.course.resortui.facade.CustomerFacade;
import org.thomasmore.oo3.course.resortui.model.CustomerPageDto;

@Named(value = "customer")
@RequestScoped

public class CustomerController {
    private CustomerPageDto dto;
    private String pageRedirect="customer.xhtml??faces-redirect=true";
    private List<CustomerEntity> selectedCustomer;
    
    @EJB
    private CustomerFacade customerFacade;
    
    @PostConstruct
    public void init() {
        
        dto = new CustomerPageDto();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String editId = req.getParameter("edit");
        String deleteId = req.getParameter("delete");
        dto = customerFacade.loadCustomerOverviewPage(editId, deleteId);
    }

    public String add(){
        customerFacade.add(dto);
        return pageRedirect;
        
        }
    
      public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Customer Selected", ((CustomerEntity) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Customer Unselected", ((CustomerEntity) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public CustomerPageDto getDto() {
        return dto;
    }

    public void setDto(CustomerPageDto dto) {
        this.dto = dto;
    }

    public List<CustomerEntity> getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(List<CustomerEntity> selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }
    
    
    
    }
