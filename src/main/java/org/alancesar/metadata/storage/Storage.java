package org.alancesar.metadata.storage;

public interface Storage {

    Item getChunk(String id, long length) throws Exception;
}
