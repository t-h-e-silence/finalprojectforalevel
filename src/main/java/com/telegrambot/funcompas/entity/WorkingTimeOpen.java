package com.telegrambot.funcompas.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;


@Embeddable
public class WorkingTimeOpen implements Serializable {

    @Column
    private Time open;

    public WorkingTimeOpen() {
    }

    public WorkingTimeOpen(Time open) {
        this.open=open;
    }

    public Time getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = Time.valueOf(open);
    }


    @Override
    public String toString() {
        return String.format("c %s", getOpen());
    }
}
