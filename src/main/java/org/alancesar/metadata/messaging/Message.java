package org.alancesar.metadata.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Message implements Serializable {
    @JsonProperty("filename")
    protected String filename;

    public String getFilename() {
        return filename;
    }
}
