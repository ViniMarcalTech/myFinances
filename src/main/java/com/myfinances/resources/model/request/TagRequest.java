package com.myfinances.resources.model.request;


import java.util.Objects;

public class TagRequest {

    private Long id;
    private String tagName;

    public TagRequest() {
    }

    public TagRequest(Long id,String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }


}
