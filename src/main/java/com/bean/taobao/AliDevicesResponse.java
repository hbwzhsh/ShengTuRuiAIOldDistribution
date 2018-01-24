package com.bean.taobao;

public class AliDevicesResponse extends AliResponse {

    private TaobaoHeader header;
    private Object payload;

    public TaobaoHeader getHeader() {
        return header;
    }

    public void setHeader(TaobaoHeader header) {
        this.header = header;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
