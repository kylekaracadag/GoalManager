package com.example.goalmanager;

public class Goal {
    private String text;

    public Goal (String text)
    {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
