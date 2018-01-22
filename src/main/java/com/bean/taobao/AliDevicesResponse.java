package com.bean.taobao;

public class AliDevicesResponse extends AliResponse {

    private Header header;
    private Object payload;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
