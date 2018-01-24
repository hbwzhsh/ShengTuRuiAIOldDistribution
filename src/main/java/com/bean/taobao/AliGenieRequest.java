package com.bean.taobao;

public class AliGenieRequest {
    private TaobaoHeader header;
    private TaobaoPayload payload;

    public TaobaoHeader getHeader() {
        return header;
    }

    public void setHeader(TaobaoHeader header) {
        this.header = header;
    }

    public TaobaoPayload getPayload() {
        return payload;
    }

    public void setPayload(TaobaoPayload payload) {
        this.payload = payload;
    }
}
