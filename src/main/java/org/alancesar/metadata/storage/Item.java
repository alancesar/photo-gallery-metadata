package org.alancesar.metadata.storage;

import org.alancesar.metadata.metadata.Header;
import org.alancesar.metadata.metadata.Metadata;

import java.io.InputStream;

public class Item {
    private final InputStream inputStream;
    private final Header header;

    public Item(InputStream is, Header header) {
        this.inputStream = is;
        this.header = header;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public Header getHeader() {
        return header;
    }
}
