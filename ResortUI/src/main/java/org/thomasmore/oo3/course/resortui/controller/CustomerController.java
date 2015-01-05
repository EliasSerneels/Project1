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
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.thomasmore.oo3.course.resortui.business.entity.CustomerEntity;
import org.thomasmore.oo3.course.resortui.dao.CustomerDao;
import org.thomasmore.oo3.course.resortui.model.CustomerPageDto;
import org.thomasmore.oo3.course.resortui.model.CustomerListDetailDto;

@Named(value = "customer")
@RequestScoped

public class CustomerController {
    private CustomerPageDto dto;
    private String pageRedirect="customer.xhtml??faces-redirect=true";
    
    @EJB
    private CustomerDao customerDao;
    
    @PostConstruct
    public void init() {
        
        
        dto = new CustomerPageDto();
        
        
        List<CustomerEntity> customers = customerDao.listAll();
        dto = new CustomerPageDto();
        
        for (CustomerEntity customer : customers) {
            CustomerListDetailDto listDetail = new CustomerListDetailDto();
            listDetail.setId(customer.getId());
            listDetail.setFirstname(customer.getFirstname());
            listDetail.setLastname(customer.getLastname());
            listDetail.setBirthdate(customer.getBirthdate());
            listDetail.setEmail(customer.getEmail());
            listDetail.setNumber(customer.getNumber());
            listDetail.setReceiveupdate(customer.isReceiveupdate());
           dto.getList().add(listDetail);
        }
    }

    public String add(){
        CustomerEntity customerEntity = null;
        // Als de id niet geset is, dan kennen we hem 1 toe
        if (dto.getDetail().getId() == null || dto.getDetail().getId().isEmpty()) {
            dto.getDetail().setId(UUID.randomUUID().toString());
        } else {
            customerEntity = customerDao.findById(dto.getDetail().getId());
        }

        if (customerEntity == null) {
            customerEntity = new CustomerEntity();

        }       
        customerEntity.setId(dto.getDetail().getId());
        System.out.println(dto.getDetail().getId());
        customerEntity.setLastname(dto.getDetail().getLastname());
        customerEntity.setFirstname(dto.getDetail().getFirstname());
        System.out.println(dto.getDetail().getBirthdate());
        customerEntity.setBirthdate(dto.getDetail().getBirthdate());
        System.out.println(dto.getDetail().getBirthdate());
        customerEntity.setEmail(dto.getDetail().getEmail());
        customerEntity.setNumber(dto.getDetail().getNumber());
        customerEntity.setReceiveupdate(dto.getDetail().isReceiveupdate());
        customerDao.save(customerEntity);
        
        return pageRedirect;
    }
    public void edit(String id) {
       CustomerEntity pe = customerDao.findById(id);
           
        dto.getDetail().setId(pe.getId());
        dto.getDetail().setFirstname(pe.getFirstname());
        dto.getDetail().setLastname(pe.getLastname());
        dto.getDetail().setBirthdate(pe.getBirthdate());
        dto.getDetail().setEmail(pe.getEmail());
        dto.getDetail().setNumber(pe.getNumber());
        dto.getDetail().setReceiveupdate(pe.isReceiveupdate());
        
    }
    
    public String remove(String id) {
        customerDao.deleteById(id);
        
        return pageRedirect;
    }
    public CustomerPageDto getDto() {
        return dto;
    }

    public void setDto(CustomerPageDto dto) {
        this.dto = dto;
    }
}
