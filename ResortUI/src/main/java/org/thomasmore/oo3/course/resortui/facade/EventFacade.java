/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.facade;


import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.thomasmore.oo3.course.resortui.business.entity.CustomerEntity;
import org.thomasmore.oo3.course.resortui.business.entity.EventEntity;
import org.thomasmore.oo3.course.resortui.business.entity.EventcompanyEntity;
import org.thomasmore.oo3.course.resortui.business.entity.EventtypeEntity;
import org.thomasmore.oo3.course.resortui.business.entity.LocationEntity;
import org.thomasmore.oo3.course.resortui.dao.CustomerDao;
import org.thomasmore.oo3.course.resortui.dao.EventDao;
import org.thomasmore.oo3.course.resortui.dao.EventcompanyDao;
import org.thomasmore.oo3.course.resortui.dao.EventtypeDao;
import org.thomasmore.oo3.course.resortui.dao.LocationDao;
import org.thomasmore.oo3.course.resortui.model.EventListDetailDto;
import org.thomasmore.oo3.course.resortui.model.EventPageDto;

@Named(value = "eventFacade")
@Stateless
public class EventFacade implements Serializable{

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
    
    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();

    private final SimpleDateFormat dateSimple = new SimpleDateFormat("dd-MM-yyyy hh:mm");
    private final SimpleDateFormat dateDate = new SimpleDateFormat("dd-MM-yyyy");
    private final SimpleDateFormat dateTime = new SimpleDateFormat("hh:mm");
    
    private boolean startAfterEnd,doubleBooking;

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        // Dubbele boeking nagaan
        List<EventEntity> eventschedule = eventDao.listAll();
        for (EventEntity evnt : eventschedule) {
            eventModel.addEvent(new DefaultScheduleEvent(evnt.getEventname(),evnt.getStartDate(),evnt.getEndDate()));
        }
    }
    
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
                dto.getDetail().setLocationName(eventEntity.getLocationName());  
                dto.getDetail().setCustomerName(eventEntity.getCustomerName());
            }
        }

        if (deleteId != null) {
            eventDao.deleteById(deleteId);
        }
        List<EventEntity> events = eventDao.listAll();
        List<EventcompanyEntity> eventcompanys = eventcompanyDao.listAll();
        List<EventtypeEntity> eventtypes = eventtypeDao.listAll();
        List<LocationEntity> locations = locationDao.listAll();
        List<CustomerEntity> customers = customerDao.listAll();

        for (CustomerEntity customer: customers) {
            dto.getCustomerList().add(customer.getFirstname());
        }
        
        for (LocationEntity location: locations) {
            dto.getLocationList().add(location.getLocationName());
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

            listDetail.setLocationName(event.getLocationName());  

            String dateStart = dateDate.format(event.getStartDate()) + " " + dateTime.format(event.getStartTime());
            String dateEnd = dateDate.format(event.getEndDate()) + " " + dateTime.format(event.getEndTime());
            listDetail.setStartDateFormatted(dateStart);
            listDetail.setEndDateFormatted(dateEnd);
            try {
                listDetail.setStartDate(dateSimple.parse(dateStart));
                listDetail.setEndDate(dateSimple.parse(dateEnd));
            } catch (ParseException ex) {
                Logger.getLogger(EventFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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
                if(evnt.getStartDate()!=null && evnt.getEndDate()!=null && dto.getDetail().getEndDate().before(evnt.getEndDate()) && dto.getDetail().getStartDate().after(evnt.getStartDate()) && dto.getDetail().getEndTime().before(evnt.getEndTime()) && dto.getDetail().getStartTime().after(evnt.getStartTime()) &&  dto.getDetail().getLocationName().equals(evnt.getLocationName()) ){
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
        eventEntity.setStartDate(dto.getDetail().getStartDate());
        
        eventEntity.setEndTime(dto.getDetail().getEndTime());
        eventEntity.setEndDate(dto.getDetail().getEndDate());
        
        eventEntity.setLocationName(dto.getDetail().getLocationName());  
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
    
    // VANAF HIER ALLE METHODEN VAN SCHEDULE
     
    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);
         
        return calendar.getTime();
    }
     
    public ScheduleModel getEventModel() {
        return eventModel;
    }
 
    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
 
        return calendar;
    }
     
    public ScheduleEvent getEvent() {
        return event;
    }
 
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
     
    public void addEvent(ActionEvent actionEvent) {
        if(event.getId() == null)
            eventModel.addEvent(event);
        else
            eventModel.updateEvent(event);
         
        event = new DefaultScheduleEvent();
    }
     
    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }
     
    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }
     
    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
         
        addMessage(message);
    }
     
    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
         
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
