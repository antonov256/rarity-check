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

