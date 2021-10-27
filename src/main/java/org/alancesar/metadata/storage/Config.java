package org.alancesar.metadata.storage;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("storage")
public class Config {
    @Value("${minio.endpoint}")
    private String minioEndpoint;
    @Value("${minio.root-user}")
    private String minioAccessKey;
    @Value("${minio.root-password}")
    private String minioSecretKey;

    @Bean
    public MinioClient minio() {
        return MinioClient.builder()
                .endpoint(minioEndpoint)
                .credentials(minioAccessKey, minioSecretKey)
                .build();
    }
}
