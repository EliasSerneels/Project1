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
import org.primefaces.push.impl.EventBusImpl;
import org.thomasmore.oo3.course.resortui.business.entity.EventEntity;
import org.thomasmore.oo3.course.resortui.business.entity.EventcompanyEntity;
import org.thomasmore.oo3.course.resortui.business.entity.EventtypeEntity;
import org.thomasmore.oo3.course.resortui.dao.EventDao;
import org.thomasmore.oo3.course.resortui.dao.EventcompanyDao;
import org.thomasmore.oo3.course.resortui.dao.EventtypeDao;
import org.thomasmore.oo3.course.resortui.model.EventListDetailDto;
import org.thomasmore.oo3.course.resortui.model.EventPageDto;

/**
 *
 * @author Jeroen
 */
@Stateless
public class EventFacade {



    @EJB
    private EventDao eventDao;
    @EJB
    private EventcompanyDao eventcompanyDao; 
    @EJB
    private EventtypeDao eventtypeDao;
    
    
    public EventPageDto loadEventOverviewPage(String editId, String deleteId) {
        EventPageDto dto = new EventPageDto();
        if (editId != null) {
            EventEntity eventEntity = eventDao.findById(editId);
            if (eventEntity != null) {
                dto.getDetail().setId(eventEntity.getId());

                dto.getDetail().setEventtype(eventEntity.getEventtype());
                
            }
        }
    
    if (deleteId != null) {
            eventDao.deleteById(deleteId);
        }
        List<EventEntity> events = eventDao.listAll();
        List<EventcompanyEntity> eventcompanys = eventcompanyDao.listAll();
        List<EventtypeEntity> eventtypes = eventtypeDao.listAll();
        
        
        dto.getEventcompanyList().add("");
        for (EventcompanyEntity eventcompany : eventcompanys) {
            dto.getEventcompanyList().add(eventcompany.getName());
        }
        
        dto.getEventtypeList().add("");
        for (EventtypeEntity eventtype : eventtypes) {
            dto.getEventtypeList().add(eventtype.getEventname());
        }
        
        for (EventEntity event : events) {
            EventListDetailDto listDetail = new EventListDetailDto();
            listDetail.setId(event.getId());

            listDetail.setEventtype(event.getEventtype());
            dto.getList().add(listDetail);
        }
        return dto;
    }
    
    public EventPageDto add(EventPageDto dto) {
        
EventEntity eventEntity = null;
        // Als de id niet geset is, dan kennen we hem 1 toe
        if (dto.getDetail().getId() == null || dto.getDetail().getId().isEmpty()) {
            dto.getDetail().setId(UUID.randomUUID().toString());
        } else {
            eventEntity = eventDao.findById(dto.getDetail().getId());
        }

        if (eventEntity == null) {
            eventEntity = new EventEntity();            
        }       
        eventEntity.setId(dto.getDetail().getId());

        eventEntity.setEventtype(dto.getDetail().getEventtype());
        eventDao.save(eventEntity);
        return dto;
    }
    
    
}
