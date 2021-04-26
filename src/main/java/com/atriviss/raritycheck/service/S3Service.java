package com.atriviss.raritycheck.service;

import com.amazonaws.services.databasemigrationservice.model.S3ResourceNotFoundException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.atriviss.raritycheck.dto_api.S3File;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@Slf4j
public class S3Service {
    @Autowired
    private AmazonS3 s3client;

    public S3File save(File file, String bucketName, String key) {
        boolean bucketExists = s3client.doesBucketExistV2(bucketName);
        if (!bucketExists) {
            S3ResourceNotFoundException e = new S3ResourceNotFoundException("Bucket " + bucketName + " does not exist!");
            log.error("Bucket {} does not exist!", bucketName, e);
            throw e;
        }

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);

        PutObjectResult putObjectResult = s3client.putObject(putObjectRequest);

        return new S3File(bucketName, key);
    }

    public boolean checkFileIsExists(S3File s3File) {
        boolean fileExists = s3client.doesObjectExist(s3File.getBucketName(), s3File.getKey());
        return fileExists;
    }

    public File downloadFile(String bucketName, String key, String tmpDir) {
        if (s3client.doesObjectExist(bucketName, key))
            throw new S3ResourceNotFoundException("downloadFile: object with key '" + key + "' not found in bucket '" + bucketName + "'");

        S3Object s3Object = s3client.getObject(bucketName, key);
        S3ObjectInputStream is = s3Object.getObjectContent();

        String s3objectKey = s3Object.getKey();
        String[] keyParts = s3objectKey.split(":");
        if(keyParts.length == 0)
            throw new S3ResourceNotFoundException("Object with key '" + key + "' found in bucket '" + bucketName + "', but key is in wrong format. Expected: filetype_prefix:user_id:timestamp:real_filename");

        String filename = keyParts[keyParts.length - 1];

        File file = new File(tmpDir, filename);
        try (OutputStream outputStream = new FileOutputStream(file)) {
            IOUtils.copy(is, outputStream);
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException", e);
        } catch (IOException e) {
            log.error("IOException", e);
        }

        return file;
    }

    public void delete(S3File s3File) {
        s3client.deleteObject(s3File.getBucketName(), s3File.getKey());
    }
}
