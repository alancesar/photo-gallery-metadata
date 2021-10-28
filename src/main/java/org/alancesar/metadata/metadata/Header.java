package org.alancesar.metadata.metadata;

import java.util.Map;

public class Header {
    private final String contentType;
    private final String etag;

    public Header(String contentType, String etag) {
        this.contentType = contentType;
        this.etag = etag;
    }

    public static Header from(Map<String, String> headers) {
        return new Header(headers.get(Headers.CONTENT_TYPE.getKey()), headers.get(Headers.ETAG.getKey()));
    }

    public String getContentType() {
        return contentType;
    }

    public String getEtag() {
        return etag;
    }
}
