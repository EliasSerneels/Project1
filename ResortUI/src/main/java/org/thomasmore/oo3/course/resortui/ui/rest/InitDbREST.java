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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.thomasmore.oo3.course.resortui.business.entity.BungalowEntity;
import org.thomasmore.oo3.course.resortui.business.entity.ParkEntity;


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
        bungalowEntity.setName("ABC");
        bungalowEntity.setType("Type 5");
        bungalowEntity.setPrice(100);
        objectsToSave.add(bungalowEntity);
        
        BungalowEntity bungalowEntity2 = new BungalowEntity();
        bungalowEntity2.setName("EDF");
        bungalowEntity2.setType("Type 1");
        bungalowEntity2.setPrice(150);
        objectsToSave.add(bungalowEntity2);
        
        BungalowEntity bungalowEntity3 = new BungalowEntity();
        bungalowEntity3.setName("Jef");
        bungalowEntity3.setType("Type 2");
        bungalowEntity3.setPrice(80);
        objectsToSave.add(bungalowEntity3);
        
        ParkEntity parkEntity = new ParkEntity();
        parkEntity.setName("Parkje 1");
        parkEntity.setLocation("Antwerpen");
        parkEntity.setCapacity(165);
        objectsToSave.add(parkEntity);
        
        for (Object objectToSave1 : objectsToSave){
            em.persist(objectToSave1);
        }
        

        return bungalowEntity;
        
    }

}

