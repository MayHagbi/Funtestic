package com.funtestic.models;

public class Quiz {

    private String grade;
    private Child child;

    public Quiz(String grade,Child chld)
    {
        this.grade = grade;
        child = new Child(chld);
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }
}
