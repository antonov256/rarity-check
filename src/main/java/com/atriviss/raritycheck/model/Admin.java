package com.atriviss.raritycheck.model;

import java.util.TimeZone;

public class Admin extends User {
    public Admin(Integer id, String username, String password, String name, String surname, String email, TimeZone timeZone, Portfolio portfolio) {
        super(id, username, password, name, surname, email, timeZone, portfolio);
    }
}

