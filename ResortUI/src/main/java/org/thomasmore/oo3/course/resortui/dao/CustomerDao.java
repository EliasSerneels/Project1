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
package org.thomasmore.oo3.course.resortui.dao;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.thomasmore.oo3.course.resortui.business.entity.CustomerEntity;

@Stateless
public class CustomerDao implements Serializable {
    @PersistenceContext
    private EntityManager em;
            
    
    public void save(CustomerEntity customerEntity) {
        em.merge(customerEntity);
    }

    public List<CustomerEntity> listAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(CustomerEntity.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }

    public CustomerEntity findById(String id) {
        return em.find(CustomerEntity.class, id);
    }

    public void deleteById(String id) {
        em.remove(findById(id));
    }
}
