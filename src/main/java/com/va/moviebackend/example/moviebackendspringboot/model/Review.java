package com.va.moviebackend.example.moviebackendspringboot.model;

import org.springframework.data.annotation.Id;

public class Review {

    @Id
    private String _id;
    private String body;


    public Review() {
    }

    public Review(String _id, String body) {
        this._id = _id;
        this.body = body;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
