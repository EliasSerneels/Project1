/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thomasmore.oo3.course.resortui.business.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private final SimpleDateFormat dateSimple = new SimpleDateFormat("dd/MM/yyyy");

    private final SimpleDateFormat timeSimple = new SimpleDateFormat("hh:mm");

    @PostConstruct
    public void init() {
        System.out.println("********************************");
        System.out.println("*********Startup Script*********");
        System.out.println("********************************");
        List<Object> objectsToSave = new LinkedList<>();
        
      BungalowEntity bungalowEntity = new BungalowEntity();
        bungalowEntity.setImageID("http://d3dn52uk7d1eh.cloudfront.net/asset/imagecache/accohead/jungalow-fotogallerij.jpg");
        bungalowEntity.setName("Zebra");
        bungalowEntity.setType("Comfort");
        bungalowEntity.setPrice(50);
        bungalowEntity.setPark("Funpark 1");
        bungalowEntity.setMaxpeople("1");
        objectsToSave.add(bungalowEntity);

        BungalowEntity bungalowEntity2 = new BungalowEntity();
        bungalowEntity2.setImageID("http://d3dn52uk7d1eh.cloudfront.net/asset/imagecache/accohead/acco-head_jungalow_gezin_2013.jpg");
        bungalowEntity2.setName("Leeuw");
        bungalowEntity2.setType("Premium");
        bungalowEntity2.setPrice(100);
        bungalowEntity2.setPark("Funpark 2");
        bungalowEntity2.setMaxpeople("8");
        objectsToSave.add(bungalowEntity2);

        BungalowEntity bungalowEntity3 = new BungalowEntity();
        bungalowEntity3.setImageID("http://www.vakantiesvoorgrotegezinnen.nl/wp-content/uploads/Vakantiepark-Dierenbos-luxe-bungalow-damhert-6persoons-vakantiehuis-vakantiesvoorgrotegezinnen-3-600x300.jpg");
        bungalowEntity3.setName("Walrus");
        bungalowEntity3.setType("Luxe");
        bungalowEntity3.setPrice(150);
        bungalowEntity3.setPark("Funpark 3");
        bungalowEntity3.setMaxpeople("5");
        objectsToSave.add(bungalowEntity3);

        ParkEntity parkEntity1 = new ParkEntity();
        parkEntity1.setImageID("http://www.bungalows-duitsland.nl/UserFiles/Image/Nedersaksen/Roompot%20Fintel%20bungalowpark%20nedersaksen.jpg");
        parkEntity1.setName("Funpark 1");
        parkEntity1.setLocation("Antwerpen");
        parkEntity1.setCapacity(165);
        objectsToSave.add(parkEntity1);

        ParkEntity parkEntity2 = new ParkEntity();
        parkEntity2.setImageID("http://d2bae186y49u92.cloudfront.net/bp/media/Parken/TDE/01/TDE_01_30947_770x260px_JPG_26238.ashx");
        parkEntity2.setName("Funpark 2");
        parkEntity2.setLocation("Mechelen");
        parkEntity2.setCapacity(385);
        objectsToSave.add(parkEntity2);
        
        ParkEntity parkEntity3 = new ParkEntity();
        parkEntity3.setImageID("http://d2bae186y49u92.cloudfront.net/bp/media/Parken/SBG/02/SBG_02_24096_770x260_jpg_20058.ashx");
        parkEntity3.setName("Funpark 3");
        parkEntity3.setLocation("Vilvoorde");
        parkEntity3.setCapacity(700);
        objectsToSave.add(parkEntity3);

        CustomerEntity ce1 = new CustomerEntity();
        ce1.setFirstname("Jos");
        ce1.setLastname("Janssens");
        ce1.setBirthdate("01/01/1980");
        ce1.setCountry("België");
        ce1.setCity("Vilvoorde");
        ce1.setStreet("Leuvensestraat");
        ce1.setHousenumber(1);
        ce1.setPhonenumber("0499203051");
        objectsToSave.add(ce1);

        CustomerEntity ce2 = new CustomerEntity();
        ce2.setFirstname("Piet");
        ce2.setLastname("Uyttebroeck");
        ce2.setBirthdate("01/01/1970");
        ce2.setCountry("België");
        ce2.setCity("Mechelen");
        ce2.setStreet("Kerkstraat");
        ce2.setHousenumber(5);
        ce2.setPhonenumber("0488992211");
        objectsToSave.add(ce2);
        
        CustomerEntity ce3 = new CustomerEntity();
        ce3.setFirstname("Thomas");
        ce3.setLastname("Coenen");
        ce3.setBirthdate("13/08/1995");
        ce3.setCountry("België");
        ce3.setCity("Peutie");
        ce3.setStreet("Kerkstraat");
        ce3.setHousenumber(18);
        ce3.setPhonenumber("0488992211");
        objectsToSave.add(ce3);

        UserEntity ue1 = new UserEntity();
        ue1.setId(UUID.randomUUID().toString());
        ue1.setUsername("Pieter");
        ue1.setPassword("Pieter");
        ue1.setImageID("http://fc08.deviantart.net/fs48/f/2009/170/9/5/Spongebob_Imagination_by_Disneycartoon.png");
        ue1.setLevel(4);
        objectsToSave.add(ue1);

        UserEntity ue2 = new UserEntity();
        ue2.setId(UUID.randomUUID().toString());
        ue2.setUsername("Timothy");
        ue2.setPassword("Timothy");
        ue2.setImageID("http://fc08.deviantart.net/fs48/f/2009/170/9/5/Spongebob_Imagination_by_Disneycartoon.png");
        ue2.setLevel(4);
        objectsToSave.add(ue2);

        UserEntity ue3 = new UserEntity();
        ue3.setId(UUID.randomUUID().toString());
        ue3.setUsername("Elias");
        ue3.setPassword("Elias");
        ue3.setImageID("http://fc08.deviantart.net/fs48/f/2009/170/9/5/Spongebob_Imagination_by_Disneycartoon.png");
        ue3.setLevel(4);
        objectsToSave.add(ue3);
        
        UserEntity ue4 = new UserEntity();
        ue4.setId(UUID.randomUUID().toString());
        ue4.setUsername("Elliot");
        ue4.setPassword("Elliot");
        ue4.setImageID("http://fc08.deviantart.net/fs48/f/2009/170/9/5/Spongebob_Imagination_by_Disneycartoon.png");
        ue4.setLevel(4);
        objectsToSave.add(ue4);
        
        UserEntity ue5 = new UserEntity();
        ue5.setId(UUID.randomUUID().toString());
        ue5.setUsername("Pepijn");
        ue5.setPassword("Pepijn");
        ue5.setImageID("http://fc08.deviantart.net/fs48/f/2009/170/9/5/Spongebob_Imagination_by_Disneycartoon.png");
        ue5.setLevel(4);
        objectsToSave.add(ue5);
        
        UserEntity ue6 = new UserEntity();
        ue6.setId(UUID.randomUUID().toString());
        ue6.setUsername("Jeroen");
        ue6.setPassword("Jeroen");
        ue6.setLevel(4);
        ue6.setImageID("http://fc08.deviantart.net/fs48/f/2009/170/9/5/Spongebob_Imagination_by_Disneycartoon.png");
        objectsToSave.add(ue6);
        
        UserEntity ue7 = new UserEntity();
        ue7.setId(UUID.randomUUID().toString());
        ue7.setUsername("Olivier");
        ue7.setImageID("http://fc08.deviantart.net/fs48/f/2009/170/9/5/Spongebob_Imagination_by_Disneycartoon.png");
        ue7.setPassword("Olivier");
        ue7.setLevel(4);
        objectsToSave.add(ue7);
        
        UserEntity ue8 = new UserEntity();
        ue8.setId(UUID.randomUUID().toString());
        ue8.setUsername("Nicolas");
        ue8.setImageID("http://fc08.deviantart.net/fs48/f/2009/170/9/5/Spongebob_Imagination_by_Disneycartoon.png");
        ue8.setPassword("Nicolas");
        ue8.setLevel(4);
        objectsToSave.add(ue8);
        
        UserEntity ue9 = new UserEntity();
        ue9.setId(UUID.randomUUID().toString());
        ue9.setUsername("Stijn");
        ue9.setPassword("Stijn");
        ue9.setImageID("http://fc08.deviantart.net/fs48/f/2009/170/9/5/Spongebob_Imagination_by_Disneycartoon.png");
        ue9.setLevel(4);
        objectsToSave.add(ue9);
        
        UserEntity ue10 = new UserEntity();
        ue10.setId(UUID.randomUUID().toString());
        ue10.setUsername("guest");
        ue10.setPassword("guest");
        ue10.setImageID("http://www.progarchives.com/forum/uploads/18319/Mr_Blonde_BandW_300.jpeg");
        ue10.setLevel(1);
        objectsToSave.add(ue10);
        
        UserEntity ue11 = new UserEntity();
        ue11.setId(UUID.randomUUID().toString());
        ue11.setUsername("user");
        ue11.setPassword("user");
        ue11.setImageID("http://www.progarchives.com/forum/uploads/18319/Mr_Blonde_BandW_300.jpeg");
        ue11.setLevel(2);
        objectsToSave.add(ue11);
        
        UserEntity ue12 = new UserEntity();
        ue12.setId(UUID.randomUUID().toString());
        ue12.setUsername("pers");
        ue12.setPassword("pers");
        ue12.setImageID("http://www.progarchives.com/forum/uploads/18319/Mr_Blonde_BandW_300.jpeg");
        ue12.setLevel(3);
        objectsToSave.add(ue12);
        
        UserEntity ue13 = new UserEntity();
        ue13.setId(UUID.randomUUID().toString());
        ue13.setUsername("admin");
        ue13.setImageID("http://www.progarchives.com/forum/uploads/18319/Mr_Blonde_BandW_300.jpeg");
        ue13.setPassword("admin");
        ue13.setLevel(4);
        objectsToSave.add(ue13);

        EventEntity ee1 = new EventEntity();
        ee1.setId(UUID.randomUUID().toString());
        ee1.setEventtype("Trouw");
        ee1.setEventcompany("Bedrijf 1");
        ee1.setEventname("Bruiloft Thomas en Jana");
        String startDateInString = "05/05/2015";
        String startTimeInString = "15:14";
        String endDateInString = "10/05/2015";
        String endTimeInString = "16:14";
        try {
            ee1.setStartDate(dateSimple.parse(startDateInString));
            ee1.setStartTime(timeSimple.parse(startTimeInString));
            ee1.setEndDate(dateSimple.parse(endDateInString));
            ee1.setEndTime(timeSimple.parse(endTimeInString));
        } catch (ParseException ex) {
            Logger.getLogger(initDbStartup.class.getName()).log(Level.SEVERE, null, ex);
        }
        ee1.setLocationName("Zaal 1");
        ee1.setCustomerName("Jos");
        
        objectsToSave.add(ee1);

        EventEntity ee2 = new EventEntity();
        ee2.setId(UUID.randomUUID().toString());
        ee2.setEventtype("Trouw");
        ee2.setEventcompany("Bedrijf 2");
        ee2.setEventname("Bruiloft Elias en Marjolein");
        //Heel belangrijk, datums moeten voor standaardevenementen worden aangemaakt, anders krijg je EJB exceptions.
        startDateInString = "03/05/2015";
        startTimeInString = "16:15";
        endDateInString = "05/05/2015";
        endTimeInString = "17:14";
        try {
            ee2.setStartDate(dateSimple.parse(startDateInString));
            ee2.setStartTime(timeSimple.parse(startTimeInString));
            ee2.setEndDate(dateSimple.parse(endDateInString));
            ee2.setEndTime(timeSimple.parse(endTimeInString));
        } catch (ParseException ex) {
            Logger.getLogger(initDbStartup.class.getName()).log(Level.SEVERE, null, ex);
        }
        ee2.setLocationName("Elias' thuis");
        ee2.setCustomerName("Piet");
        objectsToSave.add(ee2);

        EventcompanyEntity ece1 = new EventcompanyEntity();
        ece1.setId(UUID.randomUUID().toString());
        ece1.setName("Bedrijf 1");
        ece1.setPhone("0497145474");
        ece1.setCity("Mechelen");
        ece1.setStreet("Hoogstraat");
        ece1.setContact("Dhr. Maes");
        objectsToSave.add(ece1);
        
        EventcompanyEntity ece2 = new EventcompanyEntity();
        ece2.setId(UUID.randomUUID().toString());
        ece2.setName("Bedrijf 2");
        ece2.setPhone("0497856236");
        ece2.setCity("Vilvoorde");
        ece2.setStreet("Laagstraat");
        ece2.setContact("Mevr. De Bakker");
        objectsToSave.add(ece2);
        
        EventcompanyEntity ece3 = new EventcompanyEntity();
        ece3.setId(UUID.randomUUID().toString());
        ece3.setName("Bedrijf 3");
        ece3.setPhone("0497713554");
        ece3.setCity("Antwerpen");
        ece3.setStreet("Middelstraat");
        ece3.setContact("Dhr. Thomassen");
        objectsToSave.add(ece3);
        
        EventtypeEntity ete1 = new EventtypeEntity();
        ete1.setEventname("Trouw");
        objectsToSave.add(ete1);

        EventtypeEntity ete2 = new EventtypeEntity();
        ete2.setEventname("BBQ");
        objectsToSave.add(ete2);
        
        EventtypeEntity ete3 = new EventtypeEntity();
        ete3.setEventname("Communie");
        objectsToSave.add(ete3);
        
       
       ReservationEntity re1 = new ReservationEntity();
       re1.setBungalowName("Doos");
       re1.setCustomerName("Thomas Coenen");
       re1.setStartTime("16:55");
       re1.setEndTime("19:45");
       objectsToSave.add(re1);
       
       ReservationEntity re2 = new ReservationEntity();
       re2.setBungalowName("Standaard");
       re2.setCustomerName("Piet Uyttebroeck");
       re2.setStartTime("09:00");
       re2.setEndTime("20:00");
       objectsToSave.add(re2);
       
       ReservationEntity re3 = new ReservationEntity();
       re3.setBungalowName("Luxe");
       re3.setCustomerName("Jos Janssens");
       re3.setStartTime("10:00");
       re3.setEndTime("16:30");
       objectsToSave.add(re3);
       
       LocationEntity le1 = new LocationEntity();
       le1.setLocationName("Zaal 1");
       le1.setCity("Vilvoorde");
       le1.setStreet("Leuvensestraat 27");
       objectsToSave.add(le1);
       
       LocationEntity le2 = new LocationEntity();
       le2.setLocationName("Antwerpse feestzaal");
       le2.setCity("Antwerpen");
       le2.setStreet("Stationweg 15");
       objectsToSave.add(le2);
       
       LocationEntity le3 = new LocationEntity();
       le3.setLocationName("Elias' thuis");
       le3.setCity("Vilvoorde");
       le3.setStreet("Boerenstraat 114");
       objectsToSave.add(le3);
       
        StaffEntity se1 = new StaffEntity();
        se1.setFirstname("Olivier");
        se1.setLastname("Ollisen");
        se1.setBirthdate("17-02-1980");
        se1.setCountry("België");
        se1.setCity("Mechelen");
        se1.setStreet("Kerkstraat 18");
        se1.setEmail("Olivierollisen@hotmail.com");
        se1.setPhonenumber("0478523698");
        objectsToSave.add(se1);
        
        StaffEntity se2 = new StaffEntity();
        se2.setFirstname("Piet");
        se2.setLastname("Pieters");
        se2.setBirthdate("10-11-1983");
        se2.setCountry("België");
        se2.setCity("Mechelen");
        se2.setStreet("Schoolstraat 68");
        se2.setEmail("Pieterspiet@hotmail.com");
        se2.setPhonenumber("0477423638");
        objectsToSave.add(se2);
        
        StaffEntity se3 = new StaffEntity();
        se3.setFirstname("Jean");
        se3.setLastname("Keuleers");
        se3.setBirthdate("04-04-1974");
        se3.setCountry("België");
        se3.setCity("Vilvoorde");
        se3.setStreet("Stationstraat 91");
        se3.setEmail("Keuleersjan@hotmail.com");
        se3.setPhonenumber("0468123874");
        objectsToSave.add(se3);
  
        for (Object objectToSave1 : objectsToSave) {
            em.persist(objectToSave1);
        }
    }
}
