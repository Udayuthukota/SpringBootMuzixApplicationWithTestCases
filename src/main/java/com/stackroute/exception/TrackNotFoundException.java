package com.stackroute.exception;

public class TrackNotFoundException extends Exception {
    private String messsage;

    public TrackNotFoundException() {
    }

    public TrackNotFoundException(String message) {
        super(message);
        this.messsage = messsage;
    }
}
