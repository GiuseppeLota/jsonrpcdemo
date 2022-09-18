package com.martinbohm.demo.entities;

import java.io.Serializable;

public class AdviceRequest implements Serializable {

    private String topic;
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
}
