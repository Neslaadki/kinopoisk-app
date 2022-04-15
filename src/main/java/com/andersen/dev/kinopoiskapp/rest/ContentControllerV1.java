package com.andersen.dev.kinopoiskapp.rest;

import com.andersen.dev.kinopoiskapp.dto.ContentDto;
import com.andersen.dev.kinopoiskapp.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/contents/")
@RequiredArgsConstructor
public class ContentControllerV1 {

    private final ContentService contentService;

    @GetMapping("")
    public ResponseEntity<List<ContentDto>> getContents() {
        return new ResponseEntity<>(contentService.getContents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentDto> getContentsById(@PathVariable(name = "id") @Min(1) Long id) {
        return new ResponseEntity<>(contentService.getContentById(id), HttpStatus.OK);
    }


    @GetMapping(value = "/types")
    public ResponseEntity<?> getContentTypes() {
        return new ResponseEntity<>(contentService.getContentTypes(), HttpStatus.OK);
    }

    @GetMapping("/types/{id}")
    public ResponseEntity<List<ContentDto>> getContentsByType(@PathVariable(name = "id") @Min(1) Integer id) {
        return new ResponseEntity<>(contentService.getContentsByContendTypeId(id), HttpStatus.OK);
    }

    @GetMapping("/genres")
    public ResponseEntity<?> getContentGenre() {
        return new ResponseEntity<>(contentService.getGenres(), HttpStatus.OK);
    }

    @GetMapping("/genres/{id}")
    public ResponseEntity<List<ContentDto>> getContentsByGenre(@PathVariable(name = "id") @Min(1) Integer id) {
        return new ResponseEntity<>(contentService.getContentsByGenreId(id), HttpStatus.OK);
    }


    @GetMapping("/genres/{genreId}/types/{typeId}")
    public ResponseEntity<List<ContentDto>> getContentByGenreAndContentType(@PathVariable(name = "genreId") @Min(1) Integer genreId, @PathVariable(name = "typeId") @Min(1) Integer typeId) {
        return new ResponseEntity<>(contentService.getContentsByGenreIdAndTypeId(genreId, typeId), HttpStatus.OK);
    }
}
