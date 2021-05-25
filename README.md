# Rarity Check
This project is about rarity items

## Build
1. Build from sources with Gradle:
```
./gradlew clean build
```
A produced jar will be in build/libs

2. Docker build image:
```
docker build . --tag rarity_check
```

## Run
Docker run container:
```
docker run -p 8080:8080 --name rarity_check_container rarity_check
```

## Other helpful info
Pagination works like this:
```
localhost:8080/api/items?page=3&size=2&sort=id,desc&sort=title,asc
```
All parameters are optional.  
Pagination supports sorting on multiple fields.  
Server response contains page metadata and navigation links following HATEOAS principle.  
HAL pagination links: first, prev, self, next, last

How to create admin: 
1. Create regular user 
2. Change user authorities in database: from "USER" to "USER,ADMIN" 
3. Admin user is created 

How images is uploaded:
1. FilesRestController get MultipartFile from frontend
2. FilesService calls S3Service
3. S3Service uploads file into Amazon S3 storage and return bucketName and key of file
4. Frontend adds photo with bucketName and key using PhotosRestController
5. PhotosRestController save photo entity into database. Photo entity contains bucketName and key.