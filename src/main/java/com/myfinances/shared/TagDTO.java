package com.myfinances.shared;

import java.util.Objects;

public class TagDTO {
    private Long id;
    private String tagName;

    public TagDTO() {
    }

    public TagDTO(Long id, String tagName) {
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
        TagDTO tags = (TagDTO) o;
        return Objects.equals(id, tags.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
