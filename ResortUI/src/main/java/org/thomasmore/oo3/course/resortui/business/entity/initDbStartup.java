/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.business.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Elias Serneels
 */
@Singleton
@Startup
public class initDbStartup {

    @PersistenceContext(unitName = "RESORTPU")
    private EntityManager em;

    @PostConstruct
    public void init() {
        System.out.println("********************************");
        System.out.println("*********Startup Script*********");
        System.out.println("********************************");
        List<Object> objectsToSave = new LinkedList<>();
        
        BungalowEntity bungalowEntity = new BungalowEntity();
        bungalowEntity.setImageID("b1");
        bungalowEntity.setName("Bananendoos");
        bungalowEntity.setType("Type 3");
        bungalowEntity.setPrice(500000);
        bungalowEntity.setPark("Parkje 2");
        bungalowEntity.setMaxpeople("1");
        objectsToSave.add(bungalowEntity);

        BungalowEntity bungalowEntity2 = new BungalowEntity();
        bungalowEntity2.setImageID("b2");
        bungalowEntity2.setName("Tomattenbak");
        bungalowEntity2.setType("Type 2");
        bungalowEntity2.setPrice(10);
        bungalowEntity2.setPark("Parkje 1");
        bungalowEntity2.setMaxpeople("8");
        objectsToSave.add(bungalowEntity2);

        BungalowEntity bungalowEntity3 = new BungalowEntity();
        bungalowEntity3.setImageID("b3");
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
        ce1.setCity("Vilvoorde");
        ce1.setStreet("Leuvensestraat");
        ce1.setHousenumber(1);
        ce1.setPhonenumber("0499203051");
        objectsToSave.add(ce1);

        CustomerEntity ce2 = new CustomerEntity();
        ce2.setFirstname("Piet");
        ce2.setLastname("Uyttebroeck");
        ce2.setBirthdate("01/01/1970");
        ce2.setCountry("Belgium");
        ce2.setCity("Mechelen");
        ce2.setStreet("Kerkstraat");
        ce2.setHousenumber(5);
        ce2.setPhonenumber("0488992211");
        objectsToSave.add(ce2);

        UserEntity ue1 = new UserEntity(4);
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

        EventEntity ee1 = new EventEntity();
        ee1.setId(UUID.randomUUID().toString());
        ee1.setEventtype("Bruiloft");
        objectsToSave.add(ee1);

        EventEntity ee2 = new EventEntity();
        ee2.setId(UUID.randomUUID().toString());
        ee2.setEventtype("BBQ");
        objectsToSave.add(ee2);

        EventcompanyEntity ece1 = new EventcompanyEntity();
        ece1.setId(UUID.randomUUID().toString());
        ece1.setName("Bedrijf 1");
        ece1.setPhone("0497181245545454");
        ece1.setContact("dsfdsf");
        objectsToSave.add(ece1);

        EventtypeEntity ete1 = new EventtypeEntity();
        ete1.setEventname("Trouw");
        objectsToSave.add(ete1);

        EventtypeEntity ete2 = new EventtypeEntity();
        ete2.setEventname("BBQ");
        objectsToSave.add(ete2);
        
        for (Object objectToSave1 : objectsToSave) {
            em.persist(objectToSave1);
        }
    }
}
