package com.nimap.app.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include. NON_NULL)
public class CategoryResponse {

    private int cid;
    private String name;
    private Long registerDate;
    private Long updateDate;


    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Long registerDate) {
        this.registerDate = registerDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
