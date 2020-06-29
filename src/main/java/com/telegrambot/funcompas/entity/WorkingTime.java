package com.telegrambot.funcompas.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Embeddable
public class WorkingTime implements Serializable{

    @Column
    private Date open;

   @Column
    private Date close;

    public String getOpen() {
        DateFormat format =new SimpleDateFormat("HH:mm");
        return format.format(open);
    }

    public void setFrom(Date open) {
        this.open = open;
    }

    public String getClose() {
        DateFormat format =new SimpleDateFormat("HH:mm");
        return format.format(close);
    }

    public void setClose(Date to) {
        this.close = to;
    }
}
