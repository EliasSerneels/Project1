/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.facade;

import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.thomasmore.oo3.course.resortui.business.entity.EventcompanyEntity;
import org.thomasmore.oo3.course.resortui.dao.EventcompanyDao;
import org.thomasmore.oo3.course.resortui.model.EventcompanyListDetailDto;
import org.thomasmore.oo3.course.resortui.model.EventcompanyPageDto;

/**
 *
 * @author Jeroen
 */
@Stateless
public class EventcompanyFacade {

   private EventcompanyPageDto dto;

    @EJB
    private EventcompanyDao eventcompanyDao;
    

    public EventcompanyPageDto loadEventcompanyOverviewPage(String editId, String deleteId) {

        if (editId != null) {
            EventcompanyEntity eventcompanyEntity = eventcompanyDao.findById(editId);
            if (eventcompanyEntity != null) {
                dto.getDetail().setId(eventcompanyEntity.getId());
                dto.getDetail().setName(eventcompanyEntity.getName());
                dto.getDetail().setCity(eventcompanyEntity.getCity());
                dto.getDetail().setStreet(eventcompanyEntity.getStreet());
                dto.getDetail().setPhone(eventcompanyEntity.getPhone());
                dto.getDetail().setContact(eventcompanyEntity.getContact());
            }
        }
        
        
        if (deleteId != null) {
            eventcompanyDao.deleteById(deleteId);
        }
        List<EventcompanyEntity> eventcompanies = eventcompanyDao.listAll();
        dto = new EventcompanyPageDto();
        
        
        for (EventcompanyEntity eventcompany : eventcompanies) {
            EventcompanyListDetailDto listDetail = new EventcompanyListDetailDto();
            listDetail.setId(eventcompany.getId());
            listDetail.setName(eventcompany.getName());
            listDetail.setCity(eventcompany.getCity());
            listDetail.setStreet(eventcompany.getStreet());
            listDetail.setPhone(eventcompany.getPhone());
            listDetail.setContact(eventcompany.getContact());
            dto.getList().add(listDetail);
        }
        return dto;
    }

    public EventcompanyPageDto add() {
        
EventcompanyEntity eventcompanyEntity = null;
        // Als de id niet geset is, dan kennen we hem 1 toe
        if (dto.getDetail().getId() == null || dto.getDetail().getId().isEmpty()) {
            dto.getDetail().setId(UUID.randomUUID().toString());
        } else {
            eventcompanyEntity = eventcompanyDao.findById(dto.getDetail().getId());
        }

        if (eventcompanyEntity == null) {
            eventcompanyEntity = new EventcompanyEntity();            
        }       
        eventcompanyEntity.setId(dto.getDetail().getId());
        eventcompanyEntity.setName(dto.getDetail().getName());
        eventcompanyEntity.setCity(dto.getDetail().getCity());
        eventcompanyEntity.setStreet(dto.getDetail().getStreet());
        eventcompanyEntity.setPhone(dto.getDetail().getPhone());
        eventcompanyEntity.setContact(dto.getDetail().getContact());
        eventcompanyDao.save(eventcompanyEntity);
        return dto;
    }
}