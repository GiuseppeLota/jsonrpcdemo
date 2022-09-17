package com.martinbohm.demo.entities;

import java.io.Serializable;
import java.util.List;

public class Advice implements Serializable {

    private List<String> adviceList;

    public List<String> getAdviceList() {
        return adviceList;
    }

    public void setAdviceList(List<String> adviceList) {
        this.adviceList = adviceList;
    }
}
