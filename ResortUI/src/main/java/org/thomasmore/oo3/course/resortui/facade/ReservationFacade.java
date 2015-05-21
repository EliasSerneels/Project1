package org.thomasmore.oo3.course.resortui.facade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.thomasmore.oo3.course.resortui.business.entity.BungalowEntity;
import org.thomasmore.oo3.course.resortui.business.entity.CustomerEntity;
import org.thomasmore.oo3.course.resortui.business.entity.ParkEntity;
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

    @PostConstruct
    public void init() {
        schedulecontroller.LoadBungalowSchedule();
    }

    public ReservationPageDto loadReservationOverviewPage(String editId, String deleteId) {
        ReservationPageDto dto = new ReservationPageDto();
        if (editId != null) {
            ReservationEntity reservationEntity = reservationsDao.findById(editId);

            if (reservationEntity != null) {
                dto.getDetail().setId(reservationEntity.getId());
                dto.getDetail().setBungalowName(reservationEntity.getBungalow().getName());
                dto.getDetail().setCustomerName(reservationEntity.getCustomer().getFullName());
                dto.getDetail().setEndDate(reservationEntity.getEndDate());
                dto.getDetail().setEndDateFormatted(reservationEntity.getEndDateFormatted());
                dto.getDetail().setEndTime(reservationEntity.getEndTime());
                dto.getDetail().setStartDate(reservationEntity.getStartDate());
                dto.getDetail().setStartDateFormatted(reservationEntity.getStartDateFormatted());
                dto.getDetail().setStartTime(reservationEntity.getStartTime());
            }
            schedulecontroller.LoadBungalowSchedule();
        }
        if (deleteId != null) {
            reservationsDao.deleteById(deleteId);
            schedulecontroller.LoadBungalowSchedule();
        }

        List<CustomerEntity> customers = customersDao.listAll();

        for (CustomerEntity customer : customers) {
            dto.getCustomerList().add(customer.getFirstname() + " " + customer.getLastname());
        }

        List<ParkEntity> parks = parkDao.listAll();

        for (ParkEntity park : parks) {
            dto.getParkList().add(park.getName());
        }

        List<BungalowEntity> bungalows = bungalowsDao.listAll();

        for (BungalowEntity bungalow : bungalows) {
            dto.getBungalowList().add(bungalow.getName());
        }

        List<ReservationEntity> reservations = reservationsDao.listAll();

        for (ReservationEntity reservation : reservations) {
            ReservationListDetailDto listDetail = new ReservationListDetailDto();
            listDetail.setId(reservation.getId());
            listDetail.setBungalowName(reservation.getBungalow().getName());
            listDetail.setCustomerName(reservation.getCustomer().getFullName());

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
            dto.getList().add(listDetail);

        }
        return dto;
    }

    public ReservationPageDto add(ReservationPageDto dto) {
        ReservationEntity reservationEntity = null;
        //als de id niet geset is kennen we hem 1 toe;
        if (dto.getDetail().getId() == null || dto.getDetail().getId().isEmpty()) {
            dto.getDetail().setId(UUID.randomUUID().toString());
        } else {
            reservationEntity = reservationsDao.findById(dto.getDetail().getId());

        }
        if (reservationEntity == null) {
            reservationEntity = new ReservationEntity();
        }
        List<CustomerEntity> customers = customersDao.listAll();
        CustomerEntity customer = null;
        for (CustomerEntity ce : customers) {
            if (ce.getFullName().equals(dto.getDetail().getCustomerName())) {
                customer = ce;
            }
        }
        List<BungalowEntity> bungalows = bungalowsDao.listAll();
        BungalowEntity bungalow = null;
        for (BungalowEntity be : bungalows) {
            if (be.getName().equals(dto.getDetail().getBungalowName())) {
                bungalow = be;
            }
        }
        reservationEntity.setId(dto.getDetail().getId());
        reservationEntity.setBungalow(bungalow);
        reservationEntity.setCustomer(customer);
        reservationEntity.setEndDate(dto.getDetail().getEndDate());
        reservationEntity.setEndDateFormatted(dto.getDetail().getEndDateFormatted());
        reservationEntity.setEndTime(dto.getDetail().getEndTime());
        reservationEntity.setStartDate(dto.getDetail().getStartDate());
        reservationEntity.setStartDateFormatted(dto.getDetail().getStartDateFormatted());
        reservationEntity.setStartTime(dto.getDetail().getStartTime());
        reservationsDao.save(reservationEntity);

        schedulecontroller.LoadBungalowSchedule();

        return dto;
    }

    public String checkBooking(ReservationPageDto dto) {

        // Check of begintijd na eindtijd komt
        if (dto.getDetail().getStartDate().after(dto.getDetail().getEndDate())) {
            return "begindateAfterEnddate";
        }

        // Check of begintijd voor vandaag komt
        if (dto.getDetail().getStartDate().before(schedulecontroller.yesterday())) {
            return "beginDateBeforeToday";
        }

        // Dubbele boeking nagaan
        List<ReservationEntity> reservations = reservationsDao.listAll();
        for (ReservationEntity res : reservations) {

            // Voeg datum en tijd samen
            Date reservationStartDate = res.getStartDate();
            Date reservationEndDate = res.getEndDate();
            Date dtoStartDate = dto.getDetail().getStartDate();
            Date dtoEndDate = dto.getDetail().getEndDate();

            System.out.println(reservationStartDate + ", " + reservationEndDate + ", " + dtoStartDate + ", " + dtoEndDate);

            // Checkt of datum en tijd tussen een reeds geboekte tijd ligt voor een bepaalde locatie
            // OPGELET! De !=null moet aanwezig zijn want anders wordt een nullpointer gegeven wanneer vergeleken wordt met een lege waarde              
            if (// Nullpointers vermijden
                    reservationStartDate != null
                    && reservationEndDate != null
                    && res.getBungalow().getName() != null
                    && // Check of gekozen datum niet dezelfde datum is dan begin of einddatum 
                    (dtoStartDate.equals(reservationStartDate) && dto.getDetail().getBungalowName().equals(res.getBungalow().getName()))
                    || (dtoEndDate.equals(reservationEndDate) && dto.getDetail().getBungalowName().equals(res.getBungalow().getName()))
                    || (dtoStartDate.equals(reservationEndDate) && dto.getDetail().getBungalowName().equals(res.getBungalow().getName()))
                    || (dtoEndDate.equals(reservationStartDate) && dto.getDetail().getBungalowName().equals(res.getBungalow().getName()))
                    || // Als reservation tussen begin en einddatum ligt en Zelfde locatie heeft
                    (dtoStartDate.before(reservationStartDate) && dtoEndDate.after(reservationEndDate) && dto.getDetail().getBungalowName().equals(res.getBungalow().getName()))
                    || // Als begintijd = binnen range of eindtijd is binnen range     
                    (dtoStartDate.after(reservationStartDate) && dtoStartDate.before(reservationEndDate) && dto.getDetail().getBungalowName().equals(res.getBungalow().getName()))
                    || (dtoEndDate.after(reservationStartDate) && dtoEndDate.before(reservationEndDate) && dto.getDetail().getBungalowName().equals(res.getBungalow().getName()))) {
                return "doubleBooking: <p>Locatie " + res.getBungalow().getName() + " is reeds volboekt van</p><p>" + res.getStartDateFormatted() + " tot " + res.getEndDateFormatted() + ".</p><p>Gelieve andere datum te selecteren.</p>";
            }
        }

        add(dto);
        return "";
    }

}
