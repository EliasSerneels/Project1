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
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.thomasmore.oo3.course.resortui.business.entity.ParkEntity;
import org.thomasmore.oo3.course.resortui.dao.ParkDao;
import org.thomasmore.oo3.course.resortui.model.ParkPageDto;
import org.thomasmore.oo3.course.resortui.model.ParkListDetailDto;

/**
 *
 * @author lucs
 */
@Named(value = "park")
@RequestScoped
public class ParkController {

    private ParkPageDto dto;
    private String pageRedirect="park.xhtml??faces-redirect=true";

    @EJB
    private ParkDao parkDao;

    @PostConstruct
    public void init() {

        dto = new ParkPageDto();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String editId = req.getParameter("edit");

        
        
        List<ParkEntity> parks = parkDao.listAll();
        dto = new ParkPageDto();

        for (ParkEntity park : parks) {
            ParkListDetailDto listDetail = new ParkListDetailDto();
            listDetail.setId(park.getId());
            listDetail.setName(park.getName());
            listDetail.setLocation(park.getLocation());
            listDetail.setCapacity(park.getCapacity());
            dto.getList().add(listDetail);
        }
    }

    public String add() {
        
        ParkEntity parkEntity = null;
        // Als de id niet geset is, dan kennen we hem 1 toe
        if (dto.getDetail().getId() == null || dto.getDetail().getId().isEmpty()) {
            dto.getDetail().setId(UUID.randomUUID().toString());
        } else {
            parkEntity = parkDao.findById(dto.getDetail().getId());
        }

        if (parkEntity == null) {
            parkEntity = new ParkEntity();

        }       
        parkEntity.setId(dto.getDetail().getId());
        System.out.println(dto.getDetail().getId());
        parkEntity.setName(dto.getDetail().getName());
        parkEntity.setLocation(dto.getDetail().getLocation());
        parkEntity.setCapacity(dto.getDetail().getCapacity());
        parkDao.save(parkEntity);
        
        return pageRedirect;
    }

public void edit(String id) {
       ParkEntity pe = parkDao.findById(id);
           
        dto.getDetail().setId(pe.getId());
        dto.getDetail().setName(pe.getName());
        dto.getDetail().setLocation(pe.getLocation());
        dto.getDetail().setCapacity(pe.getCapacity());
    }
    
    public String remove(String id) {
        parkDao.deleteById(id);
        
        return pageRedirect;
    }

    public ParkPageDto getDto() {
        return dto;
    }

    public void setDto(ParkPageDto dto) {
        this.dto = dto;
    }

}
