package com.telegrambot.funcompas.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Time;


@Embeddable
public class WorkingTimeClose implements Serializable {

    @Column
    private Time close;

    public WorkingTimeClose() {
    }

    public WorkingTimeClose(Time close) {
        this.close = close;
    }

    public Time getClose() {
        return close;
    }

    public void setClose(String to) {
        this.close = Time.valueOf(to);
    }

    @Override
    public String toString() {
        return String.format(" до %s", getClose());
    }
}


