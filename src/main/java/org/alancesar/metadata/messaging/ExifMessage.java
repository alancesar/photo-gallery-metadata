package org.alancesar.metadata.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ExifMessage extends Message {
    @JsonProperty
    private Map<String, Map<String, String>> exif;

    public ExifMessage(String filename, Map<String, Map<String, String>> exif) {
        this.filename = filename;
        this.exif = exif;
    }

    public Map<String, Map<String, String>> getExif() {
        return exif;
    }
}
