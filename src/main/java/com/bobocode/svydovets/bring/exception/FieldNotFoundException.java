package com.bobocode.svydovets.bring.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldNotFoundException extends RuntimeException {
    public FieldNotFoundException(String message) {
        super(message);
        log.error(message);
    }

}