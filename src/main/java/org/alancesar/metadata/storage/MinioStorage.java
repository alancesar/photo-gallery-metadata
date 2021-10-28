package org.alancesar.metadata.storage;

import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import org.alancesar.metadata.metadata.Header;
import org.alancesar.metadata.metadata.Headers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MinioStorage implements Storage {
    @Value("${minio.bucket-name}")
    private String bucketName;
    private final MinioClient client;

    private static final List<String> HEADERS = Arrays.stream(Headers.values())
            .map(Headers::getKey).collect(Collectors.toList());

    public MinioStorage(MinioClient client) {
        this.client = client;
    }

    public Item getChunk(String filename, long length) throws Exception {
        var args = GetObjectArgs.builder()
                .bucket(bucketName)
                .object(filename)
                .length(length)
                .build();

        var object = client.getObject(args);
        var header = getHeader(object);
        return new Item(object, header);
    }

    private Header getHeader(GetObjectResponse object) {
        return Header.from(object.headers()
                .toMultimap()
                .entrySet()
                .stream()
                .filter(entry -> HEADERS.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream().findAny().orElse(""))));
    }
}
