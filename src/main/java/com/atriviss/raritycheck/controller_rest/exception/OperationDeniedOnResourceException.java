package com.atriviss.raritycheck.controller_rest.exception;

import org.springframework.http.HttpMethod;

public class OperationDeniedOnResourceException extends RuntimeException {
    public OperationDeniedOnResourceException(Class<?> clazz, HttpMethod method, Integer resourceId) {
        super("No access to " + method + " for resource " + clazz.getSimpleName() + " with id=" + resourceId);
    }
}
