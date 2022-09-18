package com.martinbohm.demo.entities;

import java.io.Serializable;
import java.util.List;

public class Advice implements Serializable {

    private List<String> adviceList;

    private String errorType;
    private String errorMessage;

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getAdviceList() {
        return adviceList;
    }

    public void setAdviceList(List<String> adviceList) {
        this.adviceList = adviceList;
    }
}
