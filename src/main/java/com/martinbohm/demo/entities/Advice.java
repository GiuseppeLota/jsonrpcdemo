package com.martinbohm.demo.entities;

import java.io.Serializable;
import java.util.List;

public class Advice implements Serializable{

    public Advice(List<String> advices){
        setAdviceList(advices);
    }

    private List<String> adviceList;

    public List<String> getAdviceList() {
        return adviceList;
    }

    public void setAdviceList(List<String> adviceList) {
        this.adviceList = adviceList;
    }    
}
