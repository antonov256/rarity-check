# Rarity Check
This project is about rarity items

## Build
Build from sources with Gradle:
```
./gradlew clean build
```

Docker build image:
```
docker build . --tag rarity_check
```

## Run
Docker run container:
```
docker run -p 8080:8080 --name rarity_check_container rarity_check
```
