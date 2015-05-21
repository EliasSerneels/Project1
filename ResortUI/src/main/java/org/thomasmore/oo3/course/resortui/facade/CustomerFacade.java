/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.facade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.thomasmore.oo3.course.resortui.business.entity.CustomerEntity;
import org.thomasmore.oo3.course.resortui.dao.CustomerDao;
import org.thomasmore.oo3.course.resortui.model.CustomerListDetailDto;
import org.thomasmore.oo3.course.resortui.model.CustomerPageDto;

/**
 *
 * @author Elliot
 */
@Stateless
public class CustomerFacade {

    @EJB
    private CustomerDao customerDao;

    private final SimpleDateFormat dateDate = new SimpleDateFormat("dd/MM/yyyy");

    public CustomerPageDto loadCustomerOverviewPage(String editId, String deleteId) {
        CustomerPageDto dto = new CustomerPageDto();
        if (editId != null) {
            CustomerEntity customerEntity = customerDao.findById(editId);
            if (customerEntity != null) {
                dto.getDetail().setId(customerEntity.getId());
                dto.getDetail().setFirstname(customerEntity.getFirstname());
                dto.getDetail().setLastname(customerEntity.getLastname());
                dto.getDetail().setBirthdate(customerEntity.getBirthdate());
                dto.getDetail().setBirthdateFormatted(customerEntity.getBirthdateFormatted());
                dto.getDetail().setCountry(customerEntity.getCountry());
                dto.getDetail().setCity(customerEntity.getCity());
                dto.getDetail().setStreet(customerEntity.getStreet());
                dto.getDetail().setHousenumber(customerEntity.getHousenumber());
                dto.getDetail().setPhonenumber(customerEntity.getPhonenumber());
                dto.getDetail().setEmail(customerEntity.getEmail());
                dto.getDetail().setImageID(customerEntity.getImageID());
            }
        }
        if (deleteId != null) {
            customerDao.deleteById(deleteId);
        }
        List<CustomerEntity> customers = customerDao.listAll();

        for (CustomerEntity customer : customers) {
            CustomerListDetailDto listDetail = new CustomerListDetailDto();
            listDetail.setId(customer.getId());
            listDetail.setFirstname(customer.getFirstname());
            listDetail.setLastname(customer.getLastname());
            // Datum formateren
            listDetail.setBirthdateFormatted(dateDate.format(customer.getBirthdate()));

            try {
                listDetail.setBirthdate(dateDate.parse(listDetail.getBirthdateFormatted())
                );
            } catch (ParseException ex) {
                Logger.getLogger(EventFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Tot hier wordt datum geformateerd
            listDetail.setBirthdate(customer.getBirthdate());
            listDetail.setCountry(customer.getCountry());
            listDetail.setCity(customer.getCity());
            listDetail.setStreet(customer.getStreet());
            listDetail.setHousenumber(customer.getHousenumber());
            listDetail.setPhonenumber(customer.getPhonenumber());
            listDetail.setEmail(customer.getEmail());
            listDetail.setImageID(customer.getImageID());
            dto.getList().add(listDetail);

        }
        return dto;

    }

    public CustomerPageDto add(CustomerPageDto dto) {
        CustomerEntity customerEntity = null;
        // Als de id niet geset is, dan kennen we hem 1 toe
        if (dto.getDetail().getId() == null || dto.getDetail().getId().isEmpty()) {
            dto.getDetail().setId(UUID.randomUUID().toString());
        } else {
            customerEntity = customerDao.findById(dto.getDetail().getId());

        }
        if (customerEntity == null) {
            customerEntity = new CustomerEntity();
        }
        customerEntity.setId(dto.getDetail().getId());
        customerEntity.setLastname(dto.getDetail().getLastname());
        customerEntity.setFirstname(dto.getDetail().getFirstname());
        customerEntity.setBirthdate(dto.getDetail().getBirthdate());
        customerEntity.setBirthdateFormatted(dto.getDetail().getBirthdateFormatted());
        customerEntity.setCountry(dto.getDetail().getCountry());
        customerEntity.setCity(dto.getDetail().getCity());
        customerEntity.setStreet(dto.getDetail().getStreet());
        customerEntity.setHousenumber(dto.getDetail().getHousenumber());
        customerEntity.setPhonenumber(dto.getDetail().getPhonenumber());
        customerEntity.setEmail(dto.getDetail().getEmail());
        customerDao.save(customerEntity);
        return dto;
    }

}
