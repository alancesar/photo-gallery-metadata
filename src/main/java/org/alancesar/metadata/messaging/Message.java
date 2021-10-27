package org.alancesar.metadata.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;

public class Message implements Serializable {
    @JsonProperty("filename")
    private String filename;

    @JsonProperty
    private Map<String, Map<String, String>> exif;

    public Message() {
    }

    public Message(String filename, Map<String, Map<String, String>> exif) {
        this.filename = filename;
        this.exif = exif;
    }

    public String getFilename() {
        return filename;
    }

    public Map<String, Map<String, String>> getExif() {
        return exif;
    }
}
