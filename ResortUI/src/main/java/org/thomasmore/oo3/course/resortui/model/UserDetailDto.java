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

/**
 *
 * @author lucs
 */
public class UserDetailDto {

    private String id;
    private String username;
    private String password;

    private boolean loggedIn;

    public String getUsername() {
        return username;
    }

    public void setUsername(String lname) {
        this.username = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String fname) {
        this.password = fname;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}