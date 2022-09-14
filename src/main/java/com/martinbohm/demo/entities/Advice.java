package com.martinbohm.demo.entities;

import java.util.List;

public class Advice {

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
