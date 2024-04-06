package com.guardz.alive.core.enginer.controller;

public class ControllerException extends RuntimeException {

    public static ControllerException of(String s) {
        return new ControllerException(s);
    }

    public ControllerException(String s) {
        super(s);
    }

}
