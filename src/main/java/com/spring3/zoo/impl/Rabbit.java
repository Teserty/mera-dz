package com.spring3.zoo.impl;

import com.spring3.zoo.Animal;
import com.spring3.zoo.food.Food;

public class Rabbit implements Animal {
    private Food food;
    private Integer age;
    @Override
    public void voice() {
        System.out.print("Huh");
    }

    @Override
    public void setHungry() {
        this.food = null;
    }

    @Override
    public void feed(Food food) {
        this.food=food;
    }

    @Override
    public Integer getAge() {
        return age;
    }

    @Override
    public void throwException() {

    }
}
