package com.atriviss.raritycheck.mapper;

import org.mapstruct.Mapper;

import java.util.TimeZone;

@Mapper
public class TimezoneMapper {
    public TimeZone toTimezone(String timezoneId) {
        return TimeZone.getTimeZone(timezoneId);
    }
    public String toTimezoneId(TimeZone timeZone) {
        return timeZone.getID();
    }
}
