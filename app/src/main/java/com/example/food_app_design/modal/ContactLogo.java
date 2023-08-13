package com.example.food_app_design.modal;

public class ContactLogo {
    private  int image;
    private String logoName;

    public ContactLogo(int image, String logoName) {
        this.image = image;
        this.logoName = logoName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }
}
