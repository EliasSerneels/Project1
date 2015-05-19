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
import org.thomasmore.oo3.course.resortui.business.entity.EventtypeEntity;
import org.thomasmore.oo3.course.resortui.business.entity.EventtypedetailEntity;
import org.thomasmore.oo3.course.resortui.dao.EventcompanyDao;
import org.thomasmore.oo3.course.resortui.dao.EventtypeDao;
import org.thomasmore.oo3.course.resortui.dao.EventtypedetailDao;
import org.thomasmore.oo3.course.resortui.model.EventtypedetailListDetailDto;
import org.thomasmore.oo3.course.resortui.model.EventtypedetailPageDto;

/**
 *
 * @author Jeroen
 */
@Stateless
public class EventtypedetailFacade {


    @EJB
    private EventtypedetailDao eventtypedetailDao;
    @EJB
    private EventtypeDao eventtypeDao;
    @EJB
    private EventcompanyDao eventcompanyDao;

    public EventtypedetailPageDto loadEventtypedetailOverviewPage(String editId, String deleteId) {
EventtypedetailPageDto dto = new EventtypedetailPageDto();
        if (editId != null) {
            EventtypedetailEntity eventtypedetailEntity = eventtypedetailDao.findById(editId);
            if (eventtypedetailEntity != null) {
                dto.getDetail().setId(eventtypedetailEntity.getId());
                dto.getDetail().setEventtypename(eventtypedetailEntity.getEventtypename());
                dto.getDetail().setEventcompany(eventtypedetailEntity.getEventcompany());
                
            }
        }

        if (deleteId != null) {
            eventtypedetailDao.deleteById(deleteId);
        }
        List<EventtypedetailEntity> eventtypedetails = eventtypedetailDao.listAll();
        
        List<EventtypeEntity> eventtypes = eventtypeDao.listAll();
        List<EventcompanyEntity> eventcompanys = eventcompanyDao.listAll();
        
        
        
        dto.getEventtypeList().add("");
        for (EventtypeEntity eventtype : eventtypes) {
            dto.getEventtypeList().add(eventtype.getEventname());
        }
        
        dto.getEventcompanyList().add("");
        for (EventcompanyEntity eventcompany : eventcompanys) {
            dto.getEventcompanyList().add(eventcompany.getName());
        }
        
        
        for (EventtypedetailEntity eventtypedetail : eventtypedetails) {
            EventtypedetailListDetailDto listDetail = new EventtypedetailListDetailDto();
            listDetail.setId(eventtypedetail.getId());
            listDetail.setEventtypename(eventtypedetail.getEventtypename());
            listDetail.setEventcompany(eventtypedetail.getEventcompany());
            
            dto.getList().add(listDetail);
        }
        return dto;
    }

    public EventtypedetailPageDto add(EventtypedetailPageDto dto) {
        
EventtypedetailEntity eventtypedetailEntity = null;
        // Als de id niet geset is, dan kennen we hem 1 toe
        if (dto.getDetail().getId() == null || dto.getDetail().getId().isEmpty()) {
            dto.getDetail().setId(UUID.randomUUID().toString());
        } else {
            eventtypedetailEntity = eventtypedetailDao.findById(dto.getDetail().getId());
        }

        if (eventtypedetailEntity == null) {
            eventtypedetailEntity = new EventtypedetailEntity();            
        }       
        eventtypedetailEntity.setId(dto.getDetail().getId());
        eventtypedetailEntity.setEventtypename(dto.getDetail().getEventtypename());
        eventtypedetailEntity.setEventcompany(dto.getDetail().getEventcompany());
        
        eventtypedetailDao.save(eventtypedetailEntity);
        return dto;
}
}
