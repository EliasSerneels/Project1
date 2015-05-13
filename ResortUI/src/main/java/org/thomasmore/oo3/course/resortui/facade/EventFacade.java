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
import org.thomasmore.oo3.course.resortui.business.entity.BungalowEntity;
import org.thomasmore.oo3.course.resortui.business.entity.CustomerEntity;
import org.thomasmore.oo3.course.resortui.business.entity.EventEntity;
import org.thomasmore.oo3.course.resortui.business.entity.EventcompanyEntity;
import org.thomasmore.oo3.course.resortui.business.entity.EventtypeEntity;
import org.thomasmore.oo3.course.resortui.dao.BungalowDao;
import org.thomasmore.oo3.course.resortui.dao.CustomerDao;
import org.thomasmore.oo3.course.resortui.dao.EventDao;
import org.thomasmore.oo3.course.resortui.dao.EventcompanyDao;
import org.thomasmore.oo3.course.resortui.dao.EventtypeDao;
import org.thomasmore.oo3.course.resortui.model.EventListDetailDto;
import org.thomasmore.oo3.course.resortui.model.EventPageDto;

@Stateless
public class EventFacade {

    @EJB
    private EventDao eventDao;
    @EJB
    private EventcompanyDao eventcompanyDao;
    @EJB
    private EventtypeDao eventtypeDao;
    @EJB
    private BungalowDao bungalowDao;
    @EJB
    private CustomerDao customerDao;
    
    private boolean startAfterEnd,doubleBooking;

    public EventPageDto loadEventOverviewPage(String editId, String deleteId) {
        EventPageDto dto = new EventPageDto();
        if (editId != null) {
            EventEntity eventEntity = eventDao.findById(editId);
            if (eventEntity != null) {
                dto.getDetail().setId(eventEntity.getId());
                dto.getDetail().setEventname(eventEntity.getEventname());
                dto.getDetail().setEventtype(eventEntity.getEventtype());
                dto.getDetail().setEventcompany(eventEntity.getEventcompany());
                dto.getDetail().setStartTime(eventEntity.getStartTime());
                dto.getDetail().setEndTime(eventEntity.getEndTime());
                dto.getDetail().setStartDate(eventEntity.getStartDate());
                dto.getDetail().setEndDate(eventEntity.getEndDate());
                dto.getDetail().setBungalowName(eventEntity.getBungalowName());  
                dto.getDetail().setCustomerName(eventEntity.getCustomerName());
            }
        }

        if (deleteId != null) {
            eventDao.deleteById(deleteId);
        }
        List<EventEntity> events = eventDao.listAll();
        List<EventcompanyEntity> eventcompanys = eventcompanyDao.listAll();
        List<EventtypeEntity> eventtypes = eventtypeDao.listAll();
        List<BungalowEntity> bungalows = bungalowDao.listAll();
        List<CustomerEntity> customers = customerDao.listAll();

        for (CustomerEntity customer: customers) {
            dto.getCustomerList().add(customer.getFirstname());
        }
        
        for (BungalowEntity bungalow: bungalows) {
            dto.getBungalowList().add(bungalow.getName());
        }
        
        for (EventcompanyEntity eventcompany : eventcompanys) {
            dto.getEventcompanyList().add(eventcompany.getName());
        }

        for (EventtypeEntity eventtype : eventtypes) {
            dto.getEventtypeList().add(eventtype.getEventname());
        }

        for (EventEntity event : events) {
            EventListDetailDto listDetail = new EventListDetailDto();
            listDetail.setId(event.getId());
            listDetail.setEventname(event.getEventname());
            listDetail.setEventcompany(event.getEventcompany());
            listDetail.setEventtype(event.getEventtype());
            listDetail.setStartTime(event.getStartTime());
            listDetail.setEndTime(event.getEndTime());
            listDetail.setStartDate(event.getStartDate());
            listDetail.setEndDate(event.getEndDate());
            listDetail.setBungalowName(event.getBungalowName());  
            listDetail.setCustomerName(event.getCustomerName());
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
        
        // Dubbele boeking nagaan
        List<EventEntity> eventsdc = eventDao.listAll();
        for (EventEntity evnt : eventsdc) {
                // Checkt of datum en tijd tussen een reeds geboekte tijd ligt voor een bepaalde locatie
                // OPGELET! De !=null moet aanwezig zijn want anders wordt een nullpointer gegeven wanneer vergeleken wordt met een lege waarde
                if(evnt.getStartDate()!=null && evnt.getEndDate()!=null && dto.getDetail().getEndDate().before(evnt.getEndDate()) && dto.getDetail().getStartDate().after(evnt.getStartDate()) && dto.getDetail().getEndTime().before(evnt.getEndTime()) && dto.getDetail().getStartTime().after(evnt.getStartTime()) &&  dto.getDetail().getBungalowName().equals(evnt.getBungalowName()) ){
                    System.out.println("** DUBBELE BOEKING...");
                    this.setDoubleBooking(startAfterEnd);
                    return null;
                }
        }
       
        // Check of begintijd na eindtijd komt
        if(dto.getDetail().getStartDate().after(dto.getDetail().getEndDate())){
            this.setStartAfterEnd(startAfterEnd);
            System.out.println("** begindatum > einddatum");
            return null;
        }
        
        if (eventEntity == null) {
            eventEntity = new EventEntity();
        }
        
        eventEntity.setId(dto.getDetail().getId());
        eventEntity.setEventname(dto.getDetail().getEventname());
        eventEntity.setEventcompany(dto.getDetail().getEventcompany());
        eventEntity.setEventtype(dto.getDetail().getEventtype());
        eventEntity.setStartTime(dto.getDetail().getStartTime());
        eventEntity.setEndTime(dto.getDetail().getEndTime());
        eventEntity.setStartDate(dto.getDetail().getStartDate());
        eventEntity.setEndDate(dto.getDetail().getEndDate());
        eventEntity.setBungalowName(dto.getDetail().getBungalowName());  
        eventEntity.setCustomerName(dto.getDetail().getCustomerName());
        
        eventDao.save(eventEntity);
        return dto;
    }
    
    public boolean isStartAfterEnd() {
        return startAfterEnd;
    }

    public void setStartAfterEnd(boolean startAfterEnd) {
        this.startAfterEnd = startAfterEnd;
    }

    public boolean isDoubleBooking() {
        return doubleBooking;
    }

    public void setDoubleBooking(boolean doubleBooking) {
        this.doubleBooking = doubleBooking;
    }
    

}
