package com.bean.taobao;

public class TaobaoHeader {
    private int payLoadVersion;
    private String messageId;
    private String name;
    private String namespace;

    public int getPayLoadVersion() {
        return payLoadVersion;
    }

    public void setPayLoadVersion(int payLoadVersion) {
        this.payLoadVersion = payLoadVersion;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
