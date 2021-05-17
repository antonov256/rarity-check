package com.atriviss.raritycheck.service.search;

import com.atriviss.raritycheck.controller_rest.exception.SearchQueryException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum SearchOperation {
    EQUALITY(":"),
    NEGATION("!:"),
    GREATER_THAN(">"),
    GREATER_THAN_OR_EQUALS(">:"),
    LESS_THAN("<"),
    LESS_THAN_OR_EQUALS("<:"),
    LIKE("~");

    public static List<String> codes() {
        return Arrays.stream(values())
                .map(SearchOperation::getCode)
                .collect(Collectors.toList());
    }

    @Getter
    private String code;

    SearchOperation(String code) {
        this.code = code;
    }

    public static SearchOperation fromCode(final String input) {
        switch (input) {
            case ":": return EQUALITY;
            case "!:": return NEGATION;
            case ">": return GREATER_THAN;
            case ">:": return GREATER_THAN_OR_EQUALS;
            case "<": return LESS_THAN;
            case "<:": return LESS_THAN_OR_EQUALS;
            case "~": return LIKE;
            default: throw new SearchQueryException("'" + input + "' is not operation code!");
        }
    }
}
