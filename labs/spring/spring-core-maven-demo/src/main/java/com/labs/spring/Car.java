package com.labs.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("swift")
public class Car {
    @Value("mahindra")
    private String maker;
    @Value("xUv")
    private String model;
    @Value("brown red")
    private String color;
    @Autowired
    private Engine engine;


    public Car() {

    }

    public Car(String maker, String model, String color, Engine engine) {

        .maker = maker;
        this.model = model;
        this.color = color;
        this.engine = engine;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}