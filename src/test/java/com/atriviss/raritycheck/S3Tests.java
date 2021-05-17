package com.atriviss.raritycheck;

import com.atriviss.raritycheck.dto_api.S3File;
import com.atriviss.raritycheck.service.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

@SpringBootTest
@Slf4j
class S3Tests {
	@Autowired
	private S3Service s3Service;

	@Value("${cloud.aws.s3.upload.bucket}")
	private String uploadBucketName;

	private static String filename = "cat.jpg";
	private static String path;
	private static File file;
	private static String key;

	private static S3File s3File;

	@BeforeAll
	static void init() throws URISyntaxException {
		Class<S3Tests> clazz = S3Tests.class;
		path = Paths.get(clazz.getResource("/" + filename).toURI()).toString();
		file = new File(path);
		key = System.currentTimeMillis() + "_" + file.getName();
	}

	@Test
	void uploadFile() {
		try {
			s3File = s3Service.save(file, uploadBucketName, key);
		} catch (Exception e) {
			log.error("Error while upload file '{}'", filename, e);
		}

		Assert.notNull(s3File, "s3File is null!");
	}

	@Test
	void fileExistsAfterUpload() {
		Assert.isTrue(s3Service.checkFileIsExists(s3File), "File not exists after upload");
	}

	@Test
	void deleteFile() {
		s3Service.delete(s3File);
		Assert.isTrue(!s3Service.checkFileIsExists(s3File), "File still exists after deleting");
	}
}
