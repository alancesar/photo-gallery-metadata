package org.alancesar.metadata.storage;

import org.alancesar.metadata.metadata.Metadata;

import java.io.InputStream;

public class Item {
    private final InputStream inputStream;
    private final Metadata metadata;

    public Item(InputStream is, Metadata metadata) {
        this.inputStream = is;
        this.metadata = metadata;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public Metadata getMetadata() {
        return metadata;
    }
}
