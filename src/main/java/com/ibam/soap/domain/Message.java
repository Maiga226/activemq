package com.ibam.soap.domain;


public class Message {
    private String name;
    private String email;
    private String website;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Message{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", website='" + website + '\'' +
            '}';
    }
}
