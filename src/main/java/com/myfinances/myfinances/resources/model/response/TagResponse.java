package com.myfinances.myfinances.resources.model.response;


import java.util.Objects;

public class TagResponse {
    private Long id;
    private String tagName;

    public TagResponse() {
    }

    public TagResponse(Long id, String tagName) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagResponse tags = (TagResponse) o;
        return Objects.equals(id, tags.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
