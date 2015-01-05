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
package org.thomasmore.oo3.course.resortui.model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author lucs
 */
public class ParkPageDto {

    private ParkDetailDto detail = new ParkDetailDto();

    private List<ParkListDetailDto> list = new LinkedList<>();

    public ParkDetailDto getDetail() {
        return detail;
    }

    public void setDetail(ParkDetailDto detail) {
        this.detail = detail;
    }

    public List<ParkListDetailDto> getList() {
        return list;
    }

    public void setList(List<ParkListDetailDto> list) {
        this.list = list;
    }

    
    
}
