package org.alancesar.metadata.exif;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class DrewExifExtractor implements ExifExtractor {
    public Map<String, String> extract(File file) throws ImageProcessingException, IOException {
        final var metadata = ImageMetadataReader.readMetadata(file);
        return StreamSupport
                .stream(metadata.getDirectories().spliterator(), false)
                .map(Directory::getTags)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Tag::getTagName, Tag::getDescription));
    }
}
