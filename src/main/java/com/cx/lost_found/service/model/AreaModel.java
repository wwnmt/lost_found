package com.cx.lost_found.service.model;

import java.util.StringJoiner;

public class AreaModel {

    private Integer id;

    private String area;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AreaModel.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("area='" + area + "'")
                .toString();
    }
}
