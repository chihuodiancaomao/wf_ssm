package com.itheima.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class DateType implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            dateFormat.setLenient(false);
             try {
                     return dateFormat.parse(source);
                 } catch (ParseException e) {
                     e.printStackTrace();
                 }
             return null;
    }
}
