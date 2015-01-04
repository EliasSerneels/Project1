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
import javax.inject.Named;

import org.thomasmore.oo3.course.resortui.business.entity.CustomerEntity;
import org.thomasmore.oo3.course.resortui.dao.CustomerDao;
import org.thomasmore.oo3.course.resortui.model.CustomerPageDto;
import org.thomasmore.oo3.course.resortui.model.CustomerListDetailDto;

@Named(value = "customer")
@RequestScoped

public class CustomerController {
    private CustomerPageDto dto;
    
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
            listDetail.setName(customer.getName());
            listDetail.setLocation(customer.getLocation());
            listDetail.setCapacity(customer.getCapacity());
           dto.getList().add(listDetail);
        }
    }

    public void add(){
        dto.getDetail().setId("NEW");
        dto.getList().add(dto.getDetail());
        CustomerEntity customerentity = new CustomerEntity();
        customerentity.setId(dto.getDetail().getId());
        customerentity.setName(dto.getDetail().getName());
        customerentity.setLocation(dto.getDetail().getLocation());
        customerentity.setCapacity(dto.getDetail().getCapacity());
        customerDao.save(customerentity);
    }
    
    public CustomerPageDto getDto() {
        return dto;
    }

    public void setDto(CustomerPageDto dto) {
        this.dto = dto;
    }
}
