/*
 * Copyright (C) 2014 lucs
 * Hier zijn wat extra comments...
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
package org.thomasmore.oo3.course.resortui.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.thomasmore.oo3.course.resortui.model.IndexPageDto;

/**
 *
 * @author lucs
 */
@Named(value = "index")
@RequestScoped
public class IndexController
{

    private IndexPageDto dto;
    private List<String> images;


    @PostConstruct
    public void init()
    {
        images = new ArrayList<String>();
        for (int i = 1; i <= 4; i++) {
            images.add("nature" + i + ".jpg");
        }
    }
 
    public List<String> getImages() {
        return images;
    
    }

     
 
    public IndexPageDto getDto()
    {
        return dto;
    }

    public void setDto(IndexPageDto dto)
    {
        this.dto = dto;
    }

}
