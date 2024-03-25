package com.myfinances.myfinances.resources.model.request;

import com.myfinances.myfinances.model.entities.Category;

import java.util.Objects;

public class CategoryRequest {
    private String name;
    private String description;

    public CategoryRequest() {
    }

    public CategoryRequest(String name, String description) {

        this.name = name;
        this.description = description;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
