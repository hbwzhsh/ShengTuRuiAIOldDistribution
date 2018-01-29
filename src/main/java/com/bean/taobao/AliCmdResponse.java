package com.bean.taobao;

public class AliCmdResponse extends AliResponse {

    private AliHeader header;
    private AliPayload payload;

    public AliHeader getHeader() {
        return header;
    }

    public void setHeader(AliHeader header) {
        this.header = header;
    }

    public AliPayload getPayload() {
        return payload;
    }

    public void setPayload(AliPayload payload) {
        this.payload = payload;
    }
}
