package com.spring3;

import com.spring3.configuration.AnnotationConfiguration;
import com.spring3.configuration.BeanConfiguration;
import com.spring3.zoo.Animal;
import com.spring3.zoo.AnimalAsyncService;
import com.spring3.zoo.AnimalService;
import com.spring3.zoo.Zoo;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodType;
import com.spring3.zoo.impl.AnimalAsyncServiceImpl;
import com.spring3.zoo.impl.AnimalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = getAnnotationContext();
        Zoo setterZoo = context.getBean("zoo", Zoo.class);
        setterZoo.getAnimals().forEach(Animal::setHungry);
        //setterZoo.getAnimal1().voice();
        //setterZoo.getAnimal2().voice();
        //context.publishEvent(new AnimalIsHungryEvent<Cat>(new Cat()));

        AnimalServiceImpl animalService = context.getBean("animalServiceImpl", AnimalServiceImpl.class);
        Animal cat = context.getBean("cat", Animal.class);
        for(Animal animal: setterZoo.getAnimals())
            animalService.feedAnimal(Food.builder()
                                .expiredDate(LocalDateTime.now().plusHours(3))
                                .foodType(FoodType.FISH)
                                .value(BigDecimal.valueOf(1)).build(),
                    animal);
        //cat.voice();
        /*cat.feed(
        cat.feed(Food.builder()
                .expiredDate(LocalDateTime.now().plusHours(3))
                .foodType(FoodType.FISH)
                .value(BigDecimal.valueOf(1)).build());*/
        /*animalAsyncService.feed(
                cat,
                Food.builder()
                                .expiredDate(LocalDateTime.now().plusHours(3))
                                .foodType(FoodType.FISH)
                                .value(BigDecimal.valueOf(10)).build()
                );*/
    }

    private static ApplicationContext getAnnotationContext() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.getEnvironment().setActiveProfiles("annotationConfiguration");
        annotationConfigApplicationContext.register(AnnotationConfiguration.class, BeanConfiguration.class);
        annotationConfigApplicationContext.refresh();
        return annotationConfigApplicationContext;
    }

    private static ApplicationContext getXmlContext() {
        return new ClassPathXmlApplicationContext("XmlApplicationContext.xml");
    }
}
