package com.api.cadastrofuncionarios.lambda.utils;

import org.springframework.beans.factory.annotation.Value;

import static java.util.TimeZone.getDefault;

public class UtilDate {
    @Value("${time.zone}")
    private String timeZone;

    public String getTimeZone() {
        return this.timeZone != null ? this.timeZone : getDefault().toZoneId().toString();
    }

    public String getPatternFormat() {
        return "yyyy-MM-dd'T'HH:mm:ss'Z'";
    }

}

