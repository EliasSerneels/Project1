/*
 * Copyright (C) 2014 lucs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.thomasmore.oo3.course.resortui.ui.rest;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.thomasmore.oo3.course.resortui.business.entity.BungalowEntity;
import org.thomasmore.oo3.course.resortui.business.entity.CustomerEntity;
import org.thomasmore.oo3.course.resortui.business.entity.ParkEntity;
import org.thomasmore.oo3.course.resortui.business.entity.UserEntity;


/**
 *
 * @author lucs
 */
@Stateless
@Path("init-db")
public class InitDbREST {

    @PersistenceContext(unitName = "RESORTPU")
    private EntityManager em;

    @GET
    @Produces({"application/json", "application/xml"})
    public BungalowEntity createDB() {
        List<Object> objectsToSave = new LinkedList<>();
        BungalowEntity bungalowEntity = new BungalowEntity();
        bungalowEntity.setName("Bananendoos");
        bungalowEntity.setType("Type 3");
        bungalowEntity.setPrice(500000);
        bungalowEntity.setPark("Parkje 2");
        bungalowEntity.setMaxpeople("1");
        objectsToSave.add(bungalowEntity);
        
        BungalowEntity bungalowEntity2 = new BungalowEntity();
        bungalowEntity2.setName("Tomattenbak");
        bungalowEntity2.setType("Type 2");
        bungalowEntity2.setPrice(10);
        bungalowEntity2.setPark("Parkje 1");
        bungalowEntity2.setMaxpeople("8");
        objectsToSave.add(bungalowEntity2);
        
        BungalowEntity bungalowEntity3 = new BungalowEntity();
        bungalowEntity3.setName("Paalwoning");
        bungalowEntity3.setType("Type 4");
        bungalowEntity3.setPrice(80);
        bungalowEntity3.setPark("Parkje 2");
        bungalowEntity3.setMaxpeople("5");
        objectsToSave.add(bungalowEntity3);
        
        ParkEntity parkEntity = new ParkEntity();
        parkEntity.setName("Parkje 1");
        parkEntity.setLocation("Antwerpen");
        parkEntity.setCapacity(165);
        objectsToSave.add(parkEntity);
        
        ParkEntity parkEntity2 = new ParkEntity();
        parkEntity2.setName("Parkje 2");
        parkEntity2.setLocation("Mechelen");
        parkEntity2.setCapacity(38445);
        objectsToSave.add(parkEntity2);
        
        CustomerEntity ce1 = new CustomerEntity();
        ce1.setFirstname("Jos");
        ce1.setLastname("Janssens");
        ce1.setBirthdate("01/01/1980");
        ce1.setCountry("Belgium");
        ce1.setCity("Vilmort");
        ce1.setStreet("Leuvensestraat");
        ce1.setHousenumber(1);
        ce1.setPhonenumber("0499203051");
        objectsToSave.add(ce1);
               
        CustomerEntity ce2 = new CustomerEntity();
        ce2.setFirstname("Piet");
        ce2.setLastname("Uyttebroeck");
        ce2.setBirthdate("01/01/1970");
        ce2.setCountry("Belgium");
        ce2.setCity("Malinwa");
        ce2.setStreet("Kerkstraat");
        ce2.setHousenumber(5);
        ce2.setPhonenumber("0488992211");
        objectsToSave.add(ce2);
        
        UserEntity ue1 = new UserEntity();
        ue1.setId(UUID.randomUUID().toString());
        ue1.setUsername("Pieter");
        ue1.setPassword("Pieter");
        objectsToSave.add(ue1);
        
        UserEntity ue2 = new UserEntity();
        ue2.setId(UUID.randomUUID().toString());
        ue2.setUsername("Timothy");
        ue2.setPassword("Timothy");
        objectsToSave.add(ue2);
        
        UserEntity ue3 = new UserEntity();
        ue3.setId(UUID.randomUUID().toString());
        ue3.setUsername("Elias");
        ue3.setPassword("Elias");
        objectsToSave.add(ue3);
        
        for (Object objectToSave1 : objectsToSave){
            em.persist(objectToSave1);
        }
        

        return bungalowEntity;
        
    }

}

