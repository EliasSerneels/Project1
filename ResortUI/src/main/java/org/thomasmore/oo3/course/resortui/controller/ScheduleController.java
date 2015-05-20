/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.controller;

import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import org.thomasmore.oo3.course.resortui.business.entity.EventEntity;
import org.thomasmore.oo3.course.resortui.business.entity.ReservationEntity;
import org.thomasmore.oo3.course.resortui.dao.EventDao;
import org.thomasmore.oo3.course.resortui.dao.ReservationDao;

@Named(value = "scheduler")
@Stateless
public class ScheduleController {
        
    @EJB
    private EventDao eventDao;
    @EJB
    private ReservationDao reservationDao;
    
    private final SimpleDateFormat dateSimple = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private final SimpleDateFormat dateDate = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat dateTime = new SimpleDateFormat("HH:mm");

    private ScheduleModel eventModel;
    private ScheduleModel reservationModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    
    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);
         
        return calendar.getTime();
    }

    public ScheduleModel getReservationModel() {
        return reservationModel;
    }

    public void setReservationModel(ScheduleModel reservationModel) {
        this.reservationModel = reservationModel;
    }
    
    public ScheduleModel getEventModel() {
        return eventModel;
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
    
    public String DateAndTime(Date date, Date time){
        
        Date adjustedTime = time;
        String convertedDate = dateDate.format(date) + " " + dateTime.format(adjustedTime);
           
        return convertedDate;
    }

    public Date dateTime(Date date, Date time) {
        return new Date(
                     date.getYear(), date.getMonth(), date.getDay(), 
                     time.getHours(), time.getMinutes(), time.getSeconds()
                   );
    }
    
    public Date parseDate(String dateString) throws ParseException{
        return dateSimple.parse(dateString);
    }
    
    public void LoadEventSchedule(){
        eventModel = new DefaultScheduleModel();
        // Dubbele boeking nagaan
        List<EventEntity> eventschedule = eventDao.listAll();
        
        // Deze waarden en list zijn nodig voor de kleuren te veranderen per locatie
        String loc;
        ArrayList<String> locList = new ArrayList<>();

        for (EventEntity evnt : eventschedule) { 
            // Check of de locatie al in de lijst zit
            if(!locList.contains(evnt.getLocationName()) ) {
                locList.add(evnt.getLocationName());
             }
            
            // Datum formateren
            evnt.setStartDateFormatted(DateAndTime(evnt.getStartDate(), evnt.getStartTime()));
            evnt.setEndDateFormatted(DateAndTime(evnt.getEndDate(), evnt.getEndTime()));
            
            // Geformateerde datum in event steken

            try {
                Date startDate = dateSimple.parse(evnt.getStartDateFormatted());
                Date endDate = dateSimple.parse(evnt.getEndDateFormatted());
                
                loc = "loc"+String.valueOf(locList.indexOf(evnt.getLocationName()));
                DefaultScheduleEvent evento = new DefaultScheduleEvent (evnt.getEventname(),startDate,endDate,loc);
                eventModel.addEvent(evento);
            } catch (ParseException ex) {
                Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        }
    }
    
    public void LoadBungalowSchedule(){
        reservationModel = new DefaultScheduleModel();
        // Dubbele boeking nagaan
        List<ReservationEntity> reservationschedule = reservationDao.listAll();
        
        // Deze waarden en list zijn nodig voor de kleuren te veranderen per locatie
        String loc;
        ArrayList<String> locList = new ArrayList<String>();

        for (ReservationEntity res : reservationschedule) { 
            // Check of de locatie al in de lijst zit
            if(!locList.contains(res.getBungalowName()) ) {
                locList.add(res.getBungalowName());
             }
            
            // Datum formateren
            res.setStartDateFormatted(DateAndTime(res.getStartDate(), res.getStartTime()));
            res.setEndDateFormatted(DateAndTime(res.getEndDate(), res.getEndTime()));
            
            // Geformateerde datum in event steken
            try {
                Date startDate = dateSimple.parse(res.getStartDateFormatted());
                Date endDate = dateSimple.parse(res.getEndDateFormatted());
                loc = "loc"+String.valueOf(locList.indexOf(res.getBungalowName()));
                DefaultScheduleEvent evento = new DefaultScheduleEvent (res.getBungalowName(),startDate,endDate,loc);
                reservationModel.addEvent(evento);
            } catch (ParseException ex) {
                Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String todayString(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    public Date today(){
        Date date = new Date();
        return date;
    }
    
    public Date yesterday(){
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DAY_OF_YEAR, -1); 
        Date yesterday = cal.getTime();
        return yesterday;
    }
    public Date minimumAge(){
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.YEAR, -16); 
        Date minimumAge = cal.getTime();
        return minimumAge;
    }
    public String minimumAgeString(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.YEAR, -16); 
        Date minimumAge = cal.getTime();
        return dateFormat.format(minimumAge);
    }
}
