package org.alancesar.metadata.metadata;

import java.util.Map;

public class Metadata {
    private final Header header;
    private final Map<String, Map<String, String>> exif;

    public Metadata(Header header, Map<String, Map<String, String>> exif) {
        this.header = header;
        this.exif = exif;
    }

    public String getEtag() {
        return header.getContentType();
    }

    public Map<String, Map<String, String>> getExif() {
        return exif;
    }
}
