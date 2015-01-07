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
import org.thomasmore.oo3.course.resortui.model.BungalowPageDto;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.thomasmore.oo3.course.resortui.business.entity.BungalowEntity;
import org.thomasmore.oo3.course.resortui.business.entity.ParkEntity;
import org.thomasmore.oo3.course.resortui.dao.BungalowDao;
import org.thomasmore.oo3.course.resortui.dao.ParkDao;
import org.thomasmore.oo3.course.resortui.model.BungalowListDetailDto;

/**
 *
 * @author lucs
 */
@Named(value = "bungalow")
@RequestScoped
public class BungalowController {

    private BungalowPageDto dto;
    private String pageRedirect="bungalow.xhtml??faces-redirect=true";

    @EJB
    private BungalowDao bungalowDao;
    @EJB
    private ParkDao parkDao;
    @PostConstruct
    public void init() {

        List<BungalowEntity> bungalows = bungalowDao.listAll();
        List<ParkEntity> parks = parkDao.listAll();
        dto = new BungalowPageDto();
        dto.getParkList().add("");
        for (ParkEntity park : parks) {
            dto.getParkList().add(park.getName());
        }
        for (BungalowEntity bungalow : bungalows) {
            BungalowListDetailDto listDetail = new BungalowListDetailDto();
            listDetail.setId(bungalow.getId());
            listDetail.setName(bungalow.getName());
            listDetail.setType(bungalow.getType());
            listDetail.setPrice(bungalow.getPrice());
            listDetail.setPark(bungalow.getPark());
            listDetail.setMaxpeople(bungalow.getMaxpeople());
            listDetail.setReservations(bungalow.getReservations());
            dto.getList().add(listDetail);
        }
    }

    public String add() {
        BungalowEntity bungalowEntity = null;
        // Als de id niet geset is, dan kennen we hem 1 toe
        if (dto.getDetail().getId() == null || dto.getDetail().getId().isEmpty()) {
            dto.getDetail().setId(UUID.randomUUID().toString());
        } else {
            bungalowEntity = bungalowDao.findById(dto.getDetail().getId());
        }

        if (bungalowEntity == null) {
            bungalowEntity = new BungalowEntity();            
        }       
        bungalowEntity.setId(dto.getDetail().getId());
        bungalowEntity.setName(dto.getDetail().getName());
        bungalowEntity.setPrice(dto.getDetail().getPrice());
        bungalowEntity.setType(dto.getDetail().getType());
        bungalowEntity.setPark(dto.getDetail().getPark());
        bungalowEntity.setMaxpeople(dto.getDetail().getMaxpeople());
        bungalowDao.save(bungalowEntity);
        
        return pageRedirect;
    }
    
     public void edit(String id) {
       BungalowEntity pe = bungalowDao.findById(id);
           
        dto.getDetail().setId(pe.getId());
        dto.getDetail().setName(pe.getName());
        dto.getDetail().setPrice(pe.getPrice());
        dto.getDetail().setType(pe.getType());
        dto.getDetail().setPark(pe.getPark());
        dto.getDetail().setMaxpeople(pe.getMaxpeople());
    }
    
    public String remove(String id) {
        bungalowDao.deleteById(id);
        
        return pageRedirect;
    }
   

    public BungalowPageDto getDto() {
        return dto;
    }

    public void setDto(BungalowPageDto dto) {
        this.dto = dto;
    }

}
