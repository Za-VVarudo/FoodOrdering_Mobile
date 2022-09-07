package com.death.foodorderingprm392.model;

public class ResponseModel<T> {
    private T result;
    private String message;

    public ResponseModel(T result, String message) {
        this.result = result;
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
