package com.atriviss.raritycheck.controller_rest.exception;

public class SubcategoryNotFoundException extends RuntimeException {
    public SubcategoryNotFoundException(Integer id) {
        super("Could not find subcategory " + id);
    }
}
