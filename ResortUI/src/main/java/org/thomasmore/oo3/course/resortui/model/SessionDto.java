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

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author lucs
 */
@Named
@SessionScoped
public class SessionDto implements Serializable {

    private UserDetailDto userDto;
    private int level;

    @PostConstruct
    public void init() {
        System.out.println("SessionDto - init");
        userDto = new UserDetailDto();
        userDto.setLevel(4);
    }

    public UserDetailDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDetailDto UserDto) {
        this.userDto = UserDto;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
}