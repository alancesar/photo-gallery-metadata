package org.alancesar.metadata.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.minio.messages.Event;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Message implements Serializable {
    @JsonProperty("filename")
    private String filename;

    @JsonProperty
    private Map<String, String> exif;

    public Message() {
    }

    public Message(String filename, Map<String, String> exif) {
        this.filename = filename;
        this.exif = exif;
    }

    public String getFilename() {
        return filename;
    }

    public Map<String, String> getExif() {
        return exif;
    }
}
