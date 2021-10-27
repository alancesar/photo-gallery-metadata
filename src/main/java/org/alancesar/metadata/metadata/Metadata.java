package org.alancesar.metadata.metadata;

import java.util.Map;

public class Metadata {
    private final String contentType;
    private final String etag;

    private Metadata(String contentType, String etag) {
        this.contentType = contentType;
        this.etag = etag;
    }

    public static Metadata from(Map<String, String> headers) {
        return new Metadata(headers.get(Headers.CONTENT_TYPE.getKey()), headers.get(Headers.ETAG.getKey()));
    }

    public String getContentType() {
        return contentType;
    }

    public String getEtag() {
        return etag;
    }
}
