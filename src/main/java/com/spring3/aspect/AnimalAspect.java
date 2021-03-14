package com.spring3.aspect;

import com.spring3.zoo.Animal;
import com.spring3.zoo.AnimalAsyncService;
import com.spring3.zoo.food.Food;
import com.spring3.zoo.food.FoodType;
import com.spring3.zoo.impl.AnimalAsyncServiceImpl;
import com.spring3.zoo.impl.Cat;
import com.spring3.zoo.impl.Dog;
import com.spring3.zoo.impl.Rabbit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AnimalAspect {
    private AnimalAsyncService asyncService;

    @Pointcut("execution(* com.spring3.zoo.Animal.voice())")
    public void voicePoint() {
    }

    @Pointcut("execution (* com.spring3.zoo.*.*(..))")
    public void anyAnimalMethod() {
    }

    @Pointcut("within (com.spring3.zoo.*)")
    public void anyAnimalMethodWithWithIn() {
    }

    @Pointcut("@annotation(com.spring3.aspect.annotationMarker.Marker)")
    public void annotationPointcut() {
    }

    @Pointcut("@within(com.spring3.aspect.annotationMarker.Marker)")
    public void annotationWithInPointcut() {
    }

    @Pointcut("args(food)")
    public void withArg(Food food) {
    }

    @Before(value = "anyAnimalMethod() && withArg(food)", argNames = "joinPoint, food")
    public void beforeSetFood(JoinPoint joinPoint, Food food) throws Exception {
        if(!food.getExpiredDate().isAfter(LocalDateTime.now())){
            throw new Exception("Food expired");
        }
        if (joinPoint.getTarget().getClass().equals(Cat.class)){
            System.out.println("Cat");
            if(food.getFoodType()!= FoodType.FISH){
                throw new Exception("Cat can eat only Fish");
            };
        }else if (joinPoint.getTarget().getClass().equals(Dog.class)){
            System.out.println("Dog");
            if(food.getFoodType()!= FoodType.MEAT){
                throw new Exception("Dog can eat only Meat");
            }
        }else if (joinPoint.getTarget().getClass().equals(Rabbit.class)){
            System.out.println("Rabbit");
            if(food.getFoodType()!= FoodType.CARROT){
                throw new Exception("Rabbit can eat only Carrot");
            }
        }
    }

    /*@Around(value = "anyAnimalMethod() && withArg(food)", argNames = "joinPoint,food")
    public Object around(ProceedingJoinPoint joinPoint, Food food) throws Throwable {
        Object result = null;
        //before
        try {
            result = joinPoint.proceed();
            //after returning
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            //after throwing
            throw throwable;
        }finally {
            //after
        }
        return result;
    }*/
    @After(value = "execution(* com.spring3.zoo.Animal.setHungry())")
    public void afterSetHungry(JoinPoint joinPoint){
        ((Animal)joinPoint.getTarget()).voice();
        //asyncService.addHungryAnimal((Animal) joinPoint.getTarget());
    }
    @Autowired
    public void setAsyncService(AnimalAsyncService asyncService) {
        this.asyncService = asyncService;
    }
}