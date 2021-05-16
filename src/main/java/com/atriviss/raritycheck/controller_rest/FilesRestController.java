package com.atriviss.raritycheck.controller_rest;

import com.atriviss.raritycheck.controller_rest.exception.FileUploadException;
import com.atriviss.raritycheck.dto_api.S3File;
import com.atriviss.raritycheck.model.User;
import com.atriviss.raritycheck.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FilesRestController {
    @Autowired
    private FilesService filesService;

    @Autowired
    private UserFromPrincipalExtractor userExtractor;

    private List<String> photoFileExtensions = Arrays.asList("png", "jpg", "jpeg", "gif", "tiff", "bmp", "bpg");

    @PostMapping("/files")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<EntityModel<S3File>> uploadOne(@RequestParam("file") MultipartFile file, Principal principal) throws IOException, FileUploadException {
        String originalFilename = file.getOriginalFilename();

        if(originalFilename != null && photoFileExtensions.stream().noneMatch(originalFilename::endsWith)) {
            throw new FileUploadException("Wrong file name or file extension: " + originalFilename);
        }

        User user = userExtractor.extract(principal);

        S3File savedFile = filesService.savePhoto(file, user.getId());
        EntityModel<S3File> savedFileEntityModel = EntityModel.of(savedFile);

        return new ResponseEntity<>(savedFileEntityModel, HttpStatus.CREATED);
    }

    @DeleteMapping("/files")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<EntityModel<S3File>> deleteOne(@RequestBody S3File s3File) throws IOException {
        filesService.deleteS3File(s3File);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
