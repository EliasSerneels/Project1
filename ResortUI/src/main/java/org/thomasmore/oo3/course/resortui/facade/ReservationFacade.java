package org.thomasmore.oo3.course.resortui.facade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.thomasmore.oo3.course.resortui.business.entity.ReservationEntity;
import org.thomasmore.oo3.course.resortui.controller.ScheduleController;
import org.thomasmore.oo3.course.resortui.dao.BungalowDao;
import org.thomasmore.oo3.course.resortui.dao.CustomerDao;
import org.thomasmore.oo3.course.resortui.dao.ParkDao;
import org.thomasmore.oo3.course.resortui.dao.ReservationDao;
import org.thomasmore.oo3.course.resortui.model.ReservationListDetailDto;
import org.thomasmore.oo3.course.resortui.model.ReservationPageDto;

@Stateless
public class ReservationFacade {

    @EJB
    private ScheduleController schedulecontroller;
    @EJB
    private ReservationDao reservationsDao;
    @EJB
    private BungalowDao bungalowsDao;
    @EJB
    private CustomerDao customersDao;
    @EJB
    private ParkDao parkDao;

    private final SimpleDateFormat dateDate = new SimpleDateFormat("dd/MM/yyyy");

    public ReservationPageDto loadReservationOverviewPage(String editId, String deleteId) {
        ReservationPageDto dto = new ReservationPageDto();
        if (editId != null) {
            ReservationEntity reservationEntity = reservationsDao.findById(editId);

            if (reservationEntity != null) {
                dto.getDetail().setId(reservationEntity.getId());
                dto.getDetail().setBungalowName(reservationEntity.getBungalowName());
                dto.getDetail().setCustomerName(reservationEntity.getCustomerName());
                dto.getDetail().setEndDate(reservationEntity.getEndDate());
                dto.getDetail().setEndDateFormatted(reservationEntity.getEndDateFormatted());
                dto.getDetail().setEndTime(reservationEntity.getEndTime());
                dto.getDetail().setParkName(reservationEntity.getParkName());
                dto.getDetail().setStartDate(reservationEntity.getStartDate());
                dto.getDetail().setStartDateFormatted(reservationEntity.getStartDateFormatted());
                dto.getDetail().setStartTime(reservationEntity.getStartTime());
            }
        }
    if (deleteId != null) {
            reservationsDao.deleteById(deleteId);
        }
   List<ReservationEntity> reservations = reservationsDao.listAll();

    for(ReservationEntity reservation : reservations){
        ReservationListDetailDto listDetail = new ReservationListDetailDto();
        listDetail.setId(reservation.getId());
        listDetail.setBungalowName(reservation.getBungalowName());
        listDetail.setCustomerName(reservation.getCustomerName());
        
        // Datum formateren
            listDetail.setStartDateFormatted(schedulecontroller.DateAndTime(reservation.getStartDate(), reservation.getStartTime()));
            listDetail.setEndDateFormatted(schedulecontroller.DateAndTime(reservation.getEndDate(), reservation.getEndTime()));

            try {
                listDetail.setStartDate(schedulecontroller.parseDate(listDetail.getStartDateFormatted()));
                listDetail.setStartDate(schedulecontroller.parseDate(listDetail.getEndDateFormatted()));
            } catch (ParseException ex) {
                Logger.getLogger(EventFacade.class.getName()).log(Level.SEVERE, null, ex);
            }

            listDetail.setStartDate(reservation.getStartTime());
            listDetail.setEndDate(reservation.getEndTime());
            // Tot hier wordt datum geformateerd
            
        
        listDetail.setParkName(reservation.getParkName());
        dto.getList().add(listDetail);
        
    }
    return dto;
    }

    public ReservationPageDto add(ReservationPageDto dto){
        ReservationEntity reservationEntity = null;
        //als de id niet geset is kennen we hem 1 toe;
        if (dto.getDetail().getId() == null || dto.getDetail().getId().isEmpty()) {
            dto.getDetail().setId(UUID.randomUUID().toString());
        } else {
            reservationEntity = reservationsDao.findById(dto.getDetail().getId());

        }
        if(reservationEntity == null){
            reservationEntity = new ReservationEntity();
        }
        reservationEntity.setId(dto.getDetail().getId());
        reservationEntity.setBungalowName(dto.getDetail().getBungalowName());
        reservationEntity.setCustomerName(dto.getDetail().getCustomerName());
        reservationEntity.setEndDate(dto.getDetail().getEndDate());
        reservationEntity.setEndDateFormatted(dto.getDetail().getEndDateFormatted());
        reservationEntity.setEndTime(dto.getDetail().getEndTime());
        reservationEntity.setParkName(dto.getDetail().getParkName());
        reservationEntity.setStartDate(dto.getDetail().getStartDate());
        reservationEntity.setStartDateFormatted(dto.getDetail().getStartDateFormatted());
        reservationEntity.setStartTime(dto.getDetail().getStartTime());
        reservationsDao.save(reservationEntity);
        
        schedulecontroller.LoadEventSchedule();
        
        return dto;
    }
    
}
