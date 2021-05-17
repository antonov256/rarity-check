package com.atriviss.raritycheck.service;

import com.atriviss.raritycheck.controller_rest.exception.FileUploadException;
import com.atriviss.raritycheck.dto_api.S3File;
import com.atriviss.raritycheck.dto_api.mapper.MultipartFileToFileConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class FilesService {
    @Value("${cloud.aws.s3.upload.bucket}")
    private String uploadBucketName;

    @Value("${cloud.aws.s3.upload.photos.prefix}")
    private String photosPrefix;

    @Autowired
    private S3Service s3Service;

    @Value("${java.io.tmpdir}")
    private String tmpDir;

    @Autowired
    private MultipartFileToFileConverter multipartFileToFileConverter;


    public S3File savePhoto(MultipartFile multipartFile, Integer userId) throws FileUploadException {
        try {
            File file = multipartFileToFileConverter.multipartToFile(multipartFile, multipartFile.getOriginalFilename());

            StringBuilder sb = new StringBuilder();
            sb.append(photosPrefix)
                    .append(":")
                    .append(userId)
                    .append(":")
                    .append(System.currentTimeMillis())
                    .append(":")
                    .append(multipartFile.getOriginalFilename());

            String key = sb.toString();
            S3File savedFile = s3Service.save(file, uploadBucketName, key);

            return savedFile;
        } catch (Exception e) {
            throw new FileUploadException(e);
        }
    }

    public File downloadFile(S3File s3File) {
        File file = s3Service.downloadFile(s3File.getBucketName(), s3File.getKey(), tmpDir);
        return file;
    }

    public void deleteS3File(S3File s3File) {
        s3Service.delete(s3File);
    }
}
