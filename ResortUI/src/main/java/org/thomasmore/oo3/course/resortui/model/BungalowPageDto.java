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
public class BungalowPageDto {

    private BungalowDetailDto detail = new BungalowDetailDto();

    private List<BungalowListDetailDto> list = new LinkedList<>();

    private List<String> parkList = new LinkedList<>();

    public List<String> getParkList() {
        return parkList;
    }

    public void setParkList(List<String> parkList) {
        this.parkList = parkList;
    }

    public BungalowDetailDto getDetail() {
        return detail;
    }

    public void setDetail(BungalowDetailDto detail) {
        this.detail = detail;
    }

    public List<BungalowListDetailDto> getList() {
        return list;
    }

    public void setList(List<BungalowListDetailDto> list) {
        this.list = list;
    }

    
    
}
