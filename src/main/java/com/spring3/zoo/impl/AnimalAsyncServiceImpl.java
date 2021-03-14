package com.spring3.zoo.impl;

import com.spring3.aspect.annotationMarker.Marker;
import com.spring3.zoo.Animal;
import com.spring3.zoo.AnimalAsyncService;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
@Marker
public class AnimalAsyncServiceImpl implements AnimalAsyncService {
    private Set<Animal> hungryAnimals = new HashSet<>();
    @Async
    @Override
    public void feed(Animal animal, Food food) {
        animal.feed(food);
    }

    @Override
    public void feedAll() {
        for(Animal animal: hungryAnimals){
            feed(animal, createFoodForAnimal(animal));
        }
        hungryAnimals.clear();
    }

    @Override
    public Food createFoodForAnimal(Animal animal) {
        FoodType type = null;
        if (Dog.class.equals(animal.getClass())) {
            type = FoodType.MEAT;
        } else if (Cat.class.equals(animal.getClass())) {
            type = FoodType.FISH;
        } else if (Rabbit.class.equals(animal.getClass())) {
            type = FoodType.CARROT;
        }else{
            type = FoodType.NONE;
        }
        return Food.builder().expiredDate(LocalDateTime.now().plusHours(3))
                .foodType(type)
                .value(BigDecimal.valueOf(1)).build();
    }

    @Override
    public void addHungryAnimal(Animal animal) {
        hungryAnimals.add(animal);
    }
}
