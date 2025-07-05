package com.example.demo.web.api;

public class KommentarCreateRequest {
    private String name;
    private String text;

    public String getName() { return name; }
    public String getText() { return text; }

    public void setName(String name) { this.name = name; }
    public void setText(String text) { this.text = text; }
}
