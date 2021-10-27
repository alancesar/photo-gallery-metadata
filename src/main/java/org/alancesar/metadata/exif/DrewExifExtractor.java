package org.alancesar.metadata.exif;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class DrewExifExtractor implements ExifExtractor {
    private static final String FILE_TAG = "file";
    private static final List<String> TAGS_TO_IGNORE = List.of(FILE_TAG);

    public Map<String, Map<String, String>> extract(File file) throws ImageProcessingException, IOException {
        return StreamSupport
                .stream(ImageMetadataReader.readMetadata(file).getDirectories().spliterator(), false)
                .filter(directory -> shouldIncludeDirectory(directory.getName()))
                .collect(Collectors.toMap(Directory::getName, directory -> directory.getTags().stream()
                        .collect(Collectors.toMap(Tag::getTagName, Tag::getDescription))));
    }

    private boolean shouldIncludeDirectory(String directory) {
        return !TAGS_TO_IGNORE.contains(directory.toLowerCase());
    }
}
