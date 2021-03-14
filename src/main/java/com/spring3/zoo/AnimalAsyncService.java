package com.spring3.zoo;

import com.spring3.zoo.food.Food;

public interface AnimalAsyncService {
   void feed(Animal animal, Food food);
   void feedAll();
   Food createFoodForAnimal(Animal animal);
   void addHungryAnimal(Animal animal);
}
