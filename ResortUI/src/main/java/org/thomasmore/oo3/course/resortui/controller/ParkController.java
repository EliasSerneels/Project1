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

    public void add() {
        dto.getDetail().setId(UUID.randomUUID().toString());
        dto.getList().add(dto.getDetail());
        ParkEntity parkentity = new ParkEntity();
        parkentity.setId(dto.getDetail().getId());
        parkentity.setName(dto.getDetail().getName());
        parkentity.setLocation(dto.getDetail().getLocation());
        parkentity.setCapacity(dto.getDetail().getCapacity());
        parkDao.save(parkentity);
    }

    public String save() {
        String id = dto.getDetail().getId();
        ParkEntity pe = null;
        if (id != null) {
            pe = parkDao.findById(id);
        }
        if (pe == null) {
            pe = new ParkEntity();
        }
        pe.setName(dto.getDetail().getName());
        parkDao.save(pe);
        // Forces page refresh
        return "bungalow.xhtml??faces-redirect=true";
    }

    public String remove(String id) {
       
        parkDao.deleteById(id);
        
        return "park.xhtml??faces-redirect=true";
    }

    public ParkPageDto getDto() {
        return dto;
    }

    public void setDto(ParkPageDto dto) {
        this.dto = dto;
    }

}
