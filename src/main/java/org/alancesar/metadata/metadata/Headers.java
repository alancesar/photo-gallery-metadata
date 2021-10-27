package org.alancesar.metadata.metadata;

public enum Headers {
    ETAG("etag"), CONTENT_TYPE("content-type");

    private final String key;

    Headers(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
