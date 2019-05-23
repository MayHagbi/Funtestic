package com.funtestic.models;

import java.util.ArrayList;

public class Report {

    private String id;
    private String create_at;

    public Report(String id,String create_at )
    {
        setId(id);
        setCreate_at(create_at);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }
}
