package org.alancesar.metadata.messaging;

import org.alancesar.metadata.database.Entity;
import org.alancesar.metadata.database.Repository;
import org.alancesar.metadata.exif.ExifExtractor;
import org.alancesar.metadata.metadata.Metadata;
import org.alancesar.metadata.storage.Storage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.nio.file.Files;

@Component
public class Consumer {
    public static final long CHUNK_SIZE = 1024 * 128 - 1;
    public static final String TEMP_PREFIX = "exif-";
    public static final String TEMP_SUFFIX = ".tmp";

    private final ExifExtractor extractor;
    private final Storage storage;
    private final Repository repository;
    private final Producer producer;

    public Consumer(ExifExtractor extractor, Storage storage, Repository repository, Producer producer) {
        this.extractor = extractor;
        this.storage = storage;
        this.repository = repository;
        this.producer = producer;
    }

    @RabbitListener(queues = "#{queue.name}")
    public void consumer(Message message) {
        try {
            final var filename = message.getFilename();
            final var item = storage.getChunk(filename, CHUNK_SIZE);
            final var file = Files.createTempFile(TEMP_PREFIX, TEMP_SUFFIX);
            final var fos = new FileOutputStream(file.toFile());
            item.getInputStream().transferTo(fos);

            final var header = item.getHeader();
            final var exif = extractor.extract(file.toFile());
            final var metadata = new Metadata(header, exif);
            final var entity = new Entity(filename, metadata);
            repository.save(entity);
            producer.produce(filename, exif);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
