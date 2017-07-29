package com.mobile.udem.models;

/**
 * Created by osmar on 07-28-17.
 */

public class User {
    private String id;
    private String name;
    private String address;
    private String gender;
    private String career;
    private String photo;

    public User(String id, String name, String address, String gender, String career, String photo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.career = career;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public static User getTestUser() {
        User user = new User("1", "John Smith","Managua Nicaragua",
                "Masculino","Ingenieria en sistemas", "http://www.american.edu/uploads/profiles/large/chris_palmer_profile_11.jpg");
        return user;
    }
}
