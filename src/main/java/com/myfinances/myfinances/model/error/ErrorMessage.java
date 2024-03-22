package com.myfinances.myfinances.model.error;

import java.time.Instant;

public class ErrorMessage {
    private String title;
    private Integer status;
    private String message;
    private Instant date;

    public ErrorMessage(String title, Integer status, String message, Instant date) {
        this.title = title;
        this.status = status;
        this.message = message;
        this.date = date;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
