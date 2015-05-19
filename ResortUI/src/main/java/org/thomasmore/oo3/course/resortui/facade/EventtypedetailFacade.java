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
import org.thomasmore.oo3.course.resortui.business.entity.EventCompanyEntity;
import org.thomasmore.oo3.course.resortui.business.entity.EventTypeEntity;
import org.thomasmore.oo3.course.resortui.business.entity.EventTypeDetailEntity;
import org.thomasmore.oo3.course.resortui.dao.EventCompanyDao;
import org.thomasmore.oo3.course.resortui.dao.EventTypeDao;
import org.thomasmore.oo3.course.resortui.dao.EventTypeDetailDao;
import org.thomasmore.oo3.course.resortui.model.EventTypeDetailListDetailDto;
import org.thomasmore.oo3.course.resortui.model.EventtypedetailPageDto;

/**
 *
 * @author Jeroen
 */
@Stateless
public class EventTypeDetailFacade {


    @EJB
    private EventTypeDetailDao eventtypedetailDao;
    @EJB
    private EventTypeDao eventtypeDao;
    @EJB
    private EventCompanyDao eventcompanyDao;

    public EventtypedetailPageDto loadEventtypedetailOverviewPage(String editId, String deleteId) {
EventtypedetailPageDto dto = new EventtypedetailPageDto();
        if (editId != null) {
            EventTypeDetailEntity eventtypedetailEntity = eventtypedetailDao.findById(editId);
            if (eventtypedetailEntity != null) {
                dto.getDetail().setId(eventtypedetailEntity.getId());
                dto.getDetail().setEventtypename(eventtypedetailEntity.getEventtypename());
                dto.getDetail().setEventcompany(eventtypedetailEntity.getEventcompany());
                
            }
        }

        if (deleteId != null) {
            eventtypedetailDao.deleteById(deleteId);
        }
        List<EventTypeDetailEntity> eventtypedetails = eventtypedetailDao.listAll();
        
        List<EventTypeEntity> eventtypes = eventtypeDao.listAll();
        List<EventCompanyEntity> eventcompanys = eventcompanyDao.listAll();
        
        
        
        dto.getEventtypeList().add("");
        for (EventTypeEntity eventtype : eventtypes) {
            dto.getEventtypeList().add(eventtype.getEventname());
        }
        
        dto.getEventcompanyList().add("");
        for (EventCompanyEntity eventcompany : eventcompanys) {
            dto.getEventcompanyList().add(eventcompany.getName());
        }
        
        
        for (EventTypeDetailEntity eventtypedetail : eventtypedetails) {
            EventTypeDetailListDetailDto listDetail = new EventTypeDetailListDetailDto();
            listDetail.setId(eventtypedetail.getId());
            listDetail.setEventtypename(eventtypedetail.getEventtypename());
            listDetail.setEventcompany(eventtypedetail.getEventcompany());
            
            dto.getList().add(listDetail);
        }
        return dto;
    }

    public EventtypedetailPageDto add(EventtypedetailPageDto dto) {
        
EventTypeDetailEntity eventtypedetailEntity = null;
        // Als de id niet geset is, dan kennen we hem 1 toe
        if (dto.getDetail().getId() == null || dto.getDetail().getId().isEmpty()) {
            dto.getDetail().setId(UUID.randomUUID().toString());
        } else {
            eventtypedetailEntity = eventtypedetailDao.findById(dto.getDetail().getId());
        }

        if (eventtypedetailEntity == null) {
            eventtypedetailEntity = new EventTypeDetailEntity();            
        }       
        eventtypedetailEntity.setId(dto.getDetail().getId());
        eventtypedetailEntity.setEventtypename(dto.getDetail().getEventtypename());
        eventtypedetailEntity.setEventcompany(dto.getDetail().getEventcompany());
        
        eventtypedetailDao.save(eventtypedetailEntity);
        return dto;
}
}
