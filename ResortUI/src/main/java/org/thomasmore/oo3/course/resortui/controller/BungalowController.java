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
import org.thomasmore.oo3.course.resortui.business.entity.BasicEntity;
import org.thomasmore.oo3.course.resortui.business.entity.BungalowEntity;
import org.thomasmore.oo3.course.resortui.dao.BungalowDao;
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

    @PostConstruct
    public void init() {

        List<BungalowEntity> bungalows = bungalowDao.listAll();
        dto = new BungalowPageDto();

        for (BungalowEntity bungalow : bungalows) {
            BungalowListDetailDto listDetail = new BungalowListDetailDto();
            listDetail.setId(bungalow.getId());
            listDetail.setName(bungalow.getName());
            listDetail.setType(bungalow.getType());
            listDetail.setPrice(bungalow.getPrice());
            dto.getList().add(listDetail);
        }
    }

    public void add() {
        if(dto.getDetail().getId() == null){
           dto.getDetail().setId(UUID.randomUUID().toString()); 
        }        
        dto.getList().add(dto.getDetail());
        BungalowEntity bungalowentity = new BungalowEntity();
        bungalowentity.setId(dto.getDetail().getId());
        bungalowentity.setName(dto.getDetail().getName());
        bungalowentity.setPrice(dto.getDetail().getPrice());
        bungalowentity.setType(dto.getDetail().getType());
        bungalowDao.save(bungalowentity);
    }
    
     public String edit(String id2) {
        
        String id = id2;
        BungalowEntity pe = bungalowDao.findById(id);
           
        pe.setName("");
        pe.setPrice(dto.getDetail().getPrice());
        pe.setType(dto.getDetail().getType());
        
        bungalowDao.save(pe);
        
        return pageRedirect;
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
