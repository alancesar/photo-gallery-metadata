package org.alancesar.metadata.controller;

import org.alancesar.metadata.database.Repository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("exif")
public class ExifController {
    public static final String ETAG = "ETag";
    public static final Map<String, String> ERROR_MESSAGE = Map.of("message", "file not found");

    private final Repository repository;

    public ExifController(Repository repository) {
        this.repository = repository;
    }

    @GetMapping("{filename}")
    @ResponseBody
    public Map<String, String> getExif(@PathVariable String filename, HttpServletResponse response) throws Exception {
        final var entity = repository
                .findByFilename(filename)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        final var metadata = entity.getMetadata();
        response.addHeader(ETAG, metadata.getEtag());
        return entity.getExif();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> exceptionHandler() {
        return ERROR_MESSAGE;
    }
}
