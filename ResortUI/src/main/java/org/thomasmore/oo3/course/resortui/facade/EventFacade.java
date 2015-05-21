/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.facade;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

import org.thomasmore.oo3.course.resortui.business.entity.CustomerEntity;
import org.thomasmore.oo3.course.resortui.business.entity.EventEntity;
import org.thomasmore.oo3.course.resortui.business.entity.EventcompanyEntity;
import org.thomasmore.oo3.course.resortui.business.entity.EventtypeEntity;
import org.thomasmore.oo3.course.resortui.business.entity.LocationEntity;
import org.thomasmore.oo3.course.resortui.controller.ScheduleController;
import org.thomasmore.oo3.course.resortui.dao.CustomerDao;
import org.thomasmore.oo3.course.resortui.dao.EventDao;
import org.thomasmore.oo3.course.resortui.dao.EventcompanyDao;
import org.thomasmore.oo3.course.resortui.dao.EventtypeDao;
import org.thomasmore.oo3.course.resortui.dao.LocationDao;
import org.thomasmore.oo3.course.resortui.model.EventListDetailDto;
import org.thomasmore.oo3.course.resortui.model.EventPageDto;

@Named(value = "eventFacade")
@Stateless
public class EventFacade implements Serializable {

    @EJB
    private ScheduleController schedulecontroller;
    @EJB
    private EventDao eventDao;
    @EJB
    private EventcompanyDao eventcompanyDao;
    @EJB
    private EventtypeDao eventtypeDao;
    @EJB
    private LocationDao locationDao;
    @EJB
    private CustomerDao customerDao;

    @PostConstruct
    public void init() {
        schedulecontroller.LoadEventSchedule();
    }

    public EventPageDto loadEventOverviewPage(String editId, String deleteId) {

        EventPageDto dto = new EventPageDto();
        if (editId != null) {
            EventEntity eventEntity = eventDao.findById(editId);
            if (eventEntity != null) {
                dto.getDetail().setId(eventEntity.getId());
                dto.getDetail().setEventname(eventEntity.getEventname());
                dto.getDetail().setEventtype(eventEntity.getType().getTypeName());
                dto.getDetail().setEventcompany(eventEntity.getCompany().getName());
                dto.getDetail().setStartTime(eventEntity.getStartTime());
                dto.getDetail().setEndTime(eventEntity.getEndTime());
                dto.getDetail().setStartDate(eventEntity.getStartDate());
                dto.getDetail().setEndDate(eventEntity.getEndDate());
                dto.getDetail().setLocationName(eventEntity.getLocation().getLocationName());
                dto.getDetail().setCustomerName(eventEntity.getCustomer().getFirstname() + " " + eventEntity.getCustomer().getLastname());
                dto.getDetail().setImageID(eventEntity.getImageID());
                dto.getDetail().setStaff(eventEntity.getStaff());

                // zak naar formulier
                RequestContext context = RequestContext.getCurrentInstance();
                context.scrollTo("frmEvent");
            }
        }

        if (deleteId != null) {
            eventDao.deleteById(deleteId);
            schedulecontroller.LoadEventSchedule();
        }
        List<EventEntity> events = eventDao.listAll();
        List<EventtypeEntity> eventtypes = eventtypeDao.listAll();
        List<LocationEntity> locations = locationDao.listAll();
        List<CustomerEntity> customers = customerDao.listAll();
        List<EventcompanyEntity> eventcompanys = eventcompanyDao.listAll();

        for (CustomerEntity customer : customers) {
            dto.getCustomerList().add(customer.getFirstname() + " " + customer.getLastname());
        }

        for (LocationEntity location : locations) {
            dto.getLocationList().add(location.getLocationName());
        }

        for (EventcompanyEntity eventcompany : eventcompanys) {
            dto.getEventcompanyList().add(eventcompany.getName());
        }

        for (EventtypeEntity eventtype : eventtypes) {
            dto.getEventtypeList().add(eventtype.getTypeName());
        }

        for (EventEntity event : events) {
            int ReservationCount = 0;
            for (EventcompanyEntity eventcompany : eventcompanys) {
                if (eventcompany.getName().equals(event.getCompany().getName())) {
                    ReservationCount++;
                    eventcompany.setTotalnumberevents(ReservationCount);
                }
            }
            EventListDetailDto listDetail = new EventListDetailDto();
            listDetail.setId(event.getId());
            listDetail.setEventname(event.getEventname());
            listDetail.setEventcompany(event.getCompany().getName());
            listDetail.setEventtype(event.getType().getTypeName());
            listDetail.setStaff(event.getStaff());
            listDetail.setStartTime(event.getStartTime());
            listDetail.setEndTime(event.getEndTime());
            listDetail.setLocationName(event.getLocation().getLocationName());

            // Datum formateren
            listDetail.setStartDateFormatted(schedulecontroller.DateAndTime(event.getStartDate(), event.getStartTime()));
            listDetail.setEndDateFormatted(schedulecontroller.DateAndTime(event.getEndDate(), event.getEndTime()));

            try {
                listDetail.setStartDate(schedulecontroller.parseDate(listDetail.getStartDateFormatted()));
                listDetail.setStartDate(schedulecontroller.parseDate(listDetail.getEndDateFormatted()));
            } catch (ParseException ex) {
                Logger.getLogger(EventFacade.class.getName()).log(Level.SEVERE, null, ex);
            }

            listDetail.setStartDate(event.getStartTime());
            listDetail.setEndDate(event.getEndTime());
            // Tot hier wordt datum geformateerd

            listDetail.setCustomerName(event.getCustomer().getFirstname() + " " + event.getCustomer().getLastname());
            listDetail.setImageID(event.getImageID());
            dto.getList().add(listDetail);
        }
        return dto;
    }

    public String checkBooking(EventPageDto dto) {
        // Check of begintijd na eindtijd komt
        if (dto.getDetail().getStartDate().after(dto.getDetail().getEndDate())) {
            return "begindateAfterEnddate";
        }

        // Check of begintijd voor vandaag komt
        if (dto.getDetail().getStartDate().before(schedulecontroller.yesterday())) {
            return "beginDateBeforeToday";
        }

        // Dubbele boeking nagaan
        List<EventEntity> eventsdc = eventDao.listAll();
        for (EventEntity evnt : eventsdc) {

            // Voeg datum en tijd samen
            Date eventStartDate = evnt.getStartDate();
            Date eventEndDate = evnt.getEndDate();
            Date dtoStartDate = dto.getDetail().getStartDate();
            Date dtoEndDate = dto.getDetail().getEndDate();

            System.out.println(eventStartDate + ", " + eventEndDate + ", " + dtoStartDate + ", " + dtoEndDate);

            // Checkt of datum en tijd tussen een reeds geboekte tijd ligt voor een bepaalde locatie
            // OPGELET! De !=null moet aanwezig zijn want anders wordt een nullpointer gegeven wanneer vergeleken wordt met een lege waarde              
            if (// Nullpointers vermijden
                    eventStartDate != null
                    && eventEndDate != null
                    && evnt.getLocation().getLocationName() != null
                    && // Als event tussen begin en einddatum ligt en Zelfde locatie heeft
                    (dtoStartDate.before(eventStartDate) && dtoEndDate.after(eventEndDate) && dto.getDetail().getLocationName().equals(evnt.getLocation().getLocationName()))
                    || // Als begintijd = binnen range of eindtijd is binnen range     
                    (dtoStartDate.after(eventStartDate) && dtoStartDate.before(eventEndDate) && dto.getDetail().getLocationName().equals(evnt.getLocation().getLocationName()))
                    || (dtoEndDate.after(eventStartDate) && dtoEndDate.before(eventEndDate) && dto.getDetail().getLocationName().equals(evnt.getLocation().getLocationName()))) {
                return "doubleBooking: <p>Locatie " + evnt.getLocation().getLocationName() + " is reeds volboekt van</p><p>" + evnt.getStartDateFormatted() + " tot " + evnt.getEndDateFormatted() + ".</p><p>Gelieve andere datum te selecteren.</p>";
            }
        }

        add(dto);
        return "";
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
        List<LocationEntity> locations = locationDao.listAll();
        LocationEntity location = null;
        for (LocationEntity le : locations) {
            if (le.getLocationName().equals(dto.getDetail().getLocationName())) {
                location = le;
            }
        }
        List<EventtypeEntity> types = eventtypeDao.listAll();
        EventtypeEntity type = null;
        for (EventtypeEntity ete : types) {
            if (ete.getTypeName().equals(dto.getDetail().getEventtype())) {
                type = ete;
            }
        }
        List<EventcompanyEntity> companies = eventcompanyDao.listAll();
        EventcompanyEntity company = null;
        for (EventcompanyEntity ece : companies) {
            if (ece.getName().equals(dto.getDetail().getEventcompany())) {
                company = ece;
            }
        }
        List<CustomerEntity> customers = customerDao.listAll();
        CustomerEntity customer = null;
        for (CustomerEntity ce : customers) {
            if ((ce.getFirstname() + " " + ce.getLastname()).equals(dto.getDetail().getCustomerName())) {
                customer = ce;
            }
        }
        eventEntity.setId(dto.getDetail().getId());
        eventEntity.setEventname(dto.getDetail().getEventname());
        eventEntity.setCompany(company);
        eventEntity.setType(type);
        eventEntity.setLocation(location);
        eventEntity.setCustomer(customer);

        eventEntity.setStartTime(dto.getDetail().getStartTime());
        eventEntity.setStartDate(dto.getDetail().getStartDate());

        eventEntity.setEndTime(dto.getDetail().getEndTime());
        eventEntity.setEndDate(dto.getDetail().getEndDate());

        eventDao.save(eventEntity);

        schedulecontroller.LoadEventSchedule();

        return dto;
    }
}
