package com.martinbohm.demo.dto;

import java.io.Serializable;
import java.util.List;

public class AdviceResponse implements Serializable {

    private int total_results;

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    private List<Advice> slips;

    public List<Advice> getSlips() {
        return slips;
    }

    public void setSlips(List<Advice> slips) {
        this.slips = slips;
    }
}
