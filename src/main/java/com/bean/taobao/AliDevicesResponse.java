package com.bean.taobao;

public class AliDevicesResponse extends AliResponse {

    private AliHeader header;
    private Object payload;

    public AliHeader getHeader() {
        return header;
    }

    public void setHeader(AliHeader header) {
        this.header = header;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
