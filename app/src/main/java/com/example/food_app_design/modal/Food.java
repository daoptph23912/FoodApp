package com.example.food_app_design.modal;

import java.io.Serializable;

public class Food  implements Serializable {
    private int id;
    private String title;
    private String image;
    private  String description;
    private int discount;
    private  int price;
    private  int amountBuy;
    private  int userId;
    private int idDelete;
    public Food() {
    }

    public Food(int id, String title, String image, String description, int discount, int price, int amountBuy, int userId, int idDelete) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
        this.discount = discount;
        this.price = price;
        this.amountBuy = amountBuy;
        this.userId = userId;
        this.idDelete = idDelete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIdDelete() {
        return idDelete;
    }

    public void setIdDelete(int idDelete) {
        this.idDelete = idDelete;
    }

    public int getPrice() {
        return price;
    }
    public int getAmountBuy() {
        return amountBuy;
    }

    public void setAmountBuy(int amountBuy) {
        this.amountBuy = amountBuy;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public   int totalMoney(){
        return (100-this.discount)*this.price/100;
    }
    public int total(){
        return  this.price*this.amountBuy;
    }
}
