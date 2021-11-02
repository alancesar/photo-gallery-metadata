package org.alancesar.metadata.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class OutgoingMessage implements Serializable {
    @JsonProperty("filename")
    private String filename;
    @JsonProperty("property")
    private String property;
    @JsonProperty("payload")
    private Object payload;

    public OutgoingMessage(String filename, String property, Object payload) {
        this.filename = filename;
        this.property = property;
        this.payload = payload;
    }

    public String getFilename() {
        return filename;
    }

    public String getProperty() {
        return property;
    }

    public Object getPayload() {
        return payload;
    }
}
