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
package org.thomasmore.oo3.course.resortui.business.entity;

import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author lucs
 */
@MappedSuperclass
public class BasicEntity {
    
    @Id
    protected String id = UUID.randomUUID().toString();
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
}
