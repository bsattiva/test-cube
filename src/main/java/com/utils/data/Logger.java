package com.utils.data;


import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Logger {

    @Getter
    private String className;
    private static final String MASK = "\\?";
    private static final String QUERY = "insert into log.template values('?','?','";
    private static final String QUERY_START = "insert into log.template values('";
    private static final String QUERY_MID = "','";



    private static final String END_OF_QUERY = "',NOW())";
    private static final String DELETE_QUERY = "delete from log.template where time < '?'";

    public Logger(final Class classInstance) {
        populate(classInstance);
    }

    public Logger(final String className) {
        this.className = className;
    }

    final void populate(final Class classInstance) {
        className = classInstance.getName();
    }




    private void cleanLog() {
        try {
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String shortDate = date.format(formatter);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
