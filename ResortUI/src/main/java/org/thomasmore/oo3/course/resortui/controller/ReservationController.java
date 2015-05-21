package org.thomasmore.oo3.course.resortui.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.thomasmore.oo3.course.resortui.business.entity.ReservationEntity;
import org.thomasmore.oo3.course.resortui.facade.ReservationFacade;
import org.thomasmore.oo3.course.resortui.model.ReservationPageDto;

@Named(value = "reservation")
@RequestScoped
public class ReservationController {

    private ReservationPageDto dto;
    private final String pageRedirect = "reservation.xhtml?faces-redirect=true";
    private List<ReservationEntity> selectedReservation;
    
    @EJB
    private ReservationFacade reservationFacade;

    
    @PostConstruct
    public void init() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String editId = req.getParameter("edit");
        String deleteId = req.getParameter("delete");

        dto = reservationFacade.loadReservationOverviewPage(editId, deleteId);
    }


    public String add() {

        String check = reservationFacade.checkBooking(dto);

        // Check of dubbele boeking in string staat. Zo ja moet string opgespslits worden om suggestie te krijgen.
        String suggestion = "";
        if (check.contains("doubleBooking:")) {
            suggestion = check.replace("doubleBooking: ", "");
            check = "doubleBooking";
        }

        // Zak hoe dan ook naar het formulier wanneer één van deze meldingen getoond wordt
        RequestContext context = RequestContext.getCurrentInstance();
        context.scrollTo("frmEvent");

        // check of er meldingen getoond moeten worden
        switch (check) {
            case "begindateAfterEnddate": {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("De gekozen begindatum is later dan de gekozen einddatum.");
                facesContext.addMessage(null, facesMessage);
                return null;
            }
            case "doubleBooking": {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("De gekozen locatie is reeds bezet voor deze datum. Gelieve een andere datum te kiezen.");
                facesContext.addMessage(null, facesMessage);
                // Roept de dialogbox op
                FacesMessage message = new FacesMessage("Deze locatie is reeds volzet.", suggestion);
                RequestContext.getCurrentInstance().showMessageInDialog(message);
                return null;
            }
            case "beginDateBeforeToday": {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                FacesMessage facesMessage = new FacesMessage("De datum die u gekozen hebt ligt in het verleden.");
                facesContext.addMessage(null, facesMessage);
                return null;
            }
            default:
                return "reservation.xhtml?faces-redirect=true";
        }
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public ReservationPageDto getDto() {
        return dto;
    }

    public void setDto(ReservationPageDto dto) {
        this.dto = dto;
    }

    public List<ReservationEntity> getSelectedReservation() {
        return selectedReservation;
    }

    public void setSelectedReservation(List<ReservationEntity> selectedReservation) {
        this.selectedReservation = selectedReservation;
    }

}
