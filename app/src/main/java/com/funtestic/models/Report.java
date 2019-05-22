package com.funtestic.models;

import java.util.ArrayList;

public class Report {

    private String createDate;
    private Child child;
    private ArrayList<Quiz> quizes;

    public Report(String createDate,Child child,ArrayList<Quiz> quiz)
    {
        this.createDate = createDate;
        child = new Child(child);
        quizes = new ArrayList<Quiz>();
        for(int i=0;i<quiz.size();i++)
        {
            quizes.add(quiz.get(i));
        }

    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }
}
