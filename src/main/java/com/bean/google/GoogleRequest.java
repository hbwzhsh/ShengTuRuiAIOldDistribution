package com.bean.google;

import java.util.List;

public class GoogleRequest {
    private String requestId;
    private List<GoogleInputs> inputs;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }


    public List<GoogleInputs> getInputs() {
        return inputs;
    }

    public void setInputs(List<GoogleInputs> inputs) {
        this.inputs = inputs;
    }
}
