package org.alancesar.metadata.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class IncomingMessage implements Serializable {
    @JsonProperty("filename")
    private String filename;

    public String getFilename() {
        return filename;
    }
}
