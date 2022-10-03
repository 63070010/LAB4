package com.example.week6.pojo;

import com.vaadin.flow.component.template.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("Wizard")
public class Wizard implements Serializable {
    @Id
    private String _id;
    private String sex, name, school, house, position;
    private int money;

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String get_id() {
        return _id;
    }

    public String getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public String getSchool() {
        return school;
    }

    public String getHouse() {
        return house;
    }

    public String getPosition() {
        return position;
    }

    public int getMoney() {
        return money;
    }

    public Wizard(String _id, String sex, String name, String school, String house, String position, int money) {
        this._id = _id;
        this.sex = sex;
        this.name = name;
        this.school = school;
        this.house = house;
        this.position = position;
        this.money = money;
    }
    public Wizard(){

    }
}
