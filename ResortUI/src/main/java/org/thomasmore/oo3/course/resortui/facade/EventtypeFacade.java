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
import org.thomasmore.oo3.course.resortui.business.entity.EventtypeEntity;
import org.thomasmore.oo3.course.resortui.dao.EventtypeDao;
import org.thomasmore.oo3.course.resortui.model.EventtypeListDetailDto;
import org.thomasmore.oo3.course.resortui.model.EventtypePageDto;

/**
 *
 * @author Jeroen
 */
@Stateless
public class EventtypeFacade {

    private EventtypePageDto dto;

    @EJB
    private EventtypeDao eventtypeDao;

    
public EventtypePageDto loadEventtypeOverviewPage(String editId, String deleteId) {

        if (editId != null) {
            EventtypeEntity eventtypeEntity = eventtypeDao.findById(editId);
            if (eventtypeEntity != null) {
                dto.getDetail().setId(eventtypeEntity.getId());
                dto.getDetail().setEventname(eventtypeEntity.getEventname());
                
            }
        }

        if (deleteId != null) {
            eventtypeDao.deleteById(deleteId);
        }
        List<EventtypeEntity> events = eventtypeDao.listAll();
        
        dto = new EventtypePageDto();
        
        
        for (EventtypeEntity eventtype : events) {
            EventtypeListDetailDto listDetail = new EventtypeListDetailDto();
            listDetail.setId(eventtype.getId());
            listDetail.setEventname(eventtype.getEventname());
            dto.getList().add(listDetail);
        }
        return dto;
    }

    public EventtypePageDto add() {
        
EventtypeEntity eventtypeEntity = null;
        // Als de id niet geset is, dan kennen we hem 1 toe
        if (dto.getDetail().getId() == null || dto.getDetail().getId().isEmpty()) {
            dto.getDetail().setId(UUID.randomUUID().toString());
        } else {
            eventtypeEntity = eventtypeDao.findById(dto.getDetail().getId());
        }

        if (eventtypeEntity == null) {
            eventtypeEntity = new EventtypeEntity();            
        }       
        eventtypeEntity.setId(dto.getDetail().getId());
        eventtypeEntity.setEventname(dto.getDetail().getEventname());
        eventtypeDao.save(eventtypeEntity);
        return dto;
        
    }
}
