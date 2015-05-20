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
import org.thomasmore.oo3.course.resortui.business.entity.EventTypeEntity;
import org.thomasmore.oo3.course.resortui.dao.EventTypeDao;
import org.thomasmore.oo3.course.resortui.model.EventTypeListDetailDto;
import org.thomasmore.oo3.course.resortui.model.EventTypePageDto;

/**
 *
 * @author Jeroen
 */
@Stateless
public class EventTypeFacade {

    @EJB
    private EventTypeDao eventtypeDao;

    public EventTypePageDto loadEventtypeOverviewPage(String editId, String deleteId) {
        EventTypePageDto dto = new EventTypePageDto();
        if (editId != null) {
            EventTypeEntity eventtypeEntity = eventtypeDao.findById(editId);
            if (eventtypeEntity != null) {
                dto.getDetail().setId(eventtypeEntity.getId());
                dto.getDetail().setEventname(eventtypeEntity.getEventname());

            }
        }

        if (deleteId != null) {
            eventtypeDao.deleteById(deleteId);
        }
        List<EventTypeEntity> events = eventtypeDao.listAll();

        for (EventTypeEntity eventtype : events) {
            EventTypeListDetailDto listDetail = new EventTypeListDetailDto();
            listDetail.setId(eventtype.getId());
            listDetail.setEventname(eventtype.getEventname());
            dto.getList().add(listDetail);
        }
        return dto;
    }

    public EventTypePageDto add(EventTypePageDto dto) {

        EventTypeEntity eventtypeEntity = null;
        // Als de id niet geset is, dan kennen we hem 1 toe
        if (dto.getDetail().getId() == null || dto.getDetail().getId().isEmpty()) {
            dto.getDetail().setId(UUID.randomUUID().toString());
        } else {
            eventtypeEntity = eventtypeDao.findById(dto.getDetail().getId());
        }

        if (eventtypeEntity == null) {
            eventtypeEntity = new EventTypeEntity();
        }
        eventtypeEntity.setId(dto.getDetail().getId());
        eventtypeEntity.setEventname(dto.getDetail().getEventname());
        eventtypeDao.save(eventtypeEntity);
        return dto;

    }
}
