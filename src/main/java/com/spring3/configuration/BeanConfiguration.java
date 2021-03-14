package com.spring3.configuration;

import com.spring3.zoo.Animal;
import com.spring3.zoo.AnimalAsyncService;
import com.spring3.zoo.Zoo;
import com.spring3.zoo.impl.AnimalAsyncServiceImpl;
import com.spring3.zoo.impl.Cat;
import com.spring3.zoo.impl.Dog;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.*;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

@Configuration
@ComponentScan
//@EnableAspectJAutoProxy
@Profile("beanConfigurationConfiguration")
public class BeanConfiguration {

    private final ApplicationEventPublisher eventPublisher;

    public BeanConfiguration(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Bean(name = "dog")
    public Dog createDog() {
        return new Dog();
    }

    @Bean(name = "cat")
    public Cat createCat() {
        return new Cat();
    }

    @Bean(name = "animals")
    public List<Animal> createAnimals() {
        return Arrays.asList(createCat(), createDog());
    }

    @Bean(name = "animalsUseParams")
    public List<Animal> createAnimals(Animal dog, Animal cat) {
        return Arrays.asList(dog, cat);
    }

    @Bean(name = "zoo")
    public Zoo createZoo() {
        return new Zoo(createCat(), createDog(), createAnimals());
    }
    @Bean(name="service")
    public AnimalAsyncServiceImpl createService(){
        return new AnimalAsyncServiceImpl();
    }
}
