package com.martinbohm.demo.dto;

import java.util.Date;

public class Advice {

    private int id;

    public Advice(){

    }

    public Advice(int id, String advice, Date date){
        this.id = id;
        this.advice = advice;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String advice;

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
