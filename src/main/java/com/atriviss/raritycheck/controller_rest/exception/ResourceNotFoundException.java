package com.atriviss.raritycheck.controller_rest.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, Number resourceId) {
        super("Could not find " + resourceName + " with id=" + resourceId);
    }
    public ResourceNotFoundException(Class<?> resourceClass, Number resourceId) {
        this(resourceClass.getSimpleName(), resourceId);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
