package com.atriviss.raritycheck.dto_api.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class MultipartFileToFileConverter {
    @Value("${java.io.tmpdir}")
    private String tmpDir;

    public File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {
        File convFile = new File(tmpDir, fileName);
        multipart.transferTo(convFile);
        return convFile;
    }
}
