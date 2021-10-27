package org.alancesar.metadata.database;

import org.alancesar.metadata.metadata.Metadata;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "metadata")
public class Entity {

    @Id
    private String id;
    private String filename;
    private Metadata metadata;
    private Map<String, Map<String, String>> exif;

    public Entity() {}

    public Entity(String filename, Metadata metadata, Map<String, Map<String, String>> exif) {
        this.filename = filename;
        this.metadata = metadata;
        this.exif = exif;
    }

    public String getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public Map<String, Map<String, String>> getExif() {
        return exif;
    }
}
