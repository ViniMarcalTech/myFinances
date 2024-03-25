package com.myfinances.myfinances.resources.model.request;


import java.util.Objects;

public class TagRequest {
    private String tagName;

    public TagRequest() {
    }

    public TagRequest(String tagName) {

        this.tagName = tagName;
    }


    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }


}
