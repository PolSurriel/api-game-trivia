package com.example.trivia.controller;


import com.example.trivia.api.v1.controller.BaseRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ControllerInheritanceTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void allControllersShouldExtendBaseRestController() {
        // Obtain all beans of type BaseRestController
        Map<String, Object> controllers = applicationContext.getBeansWithAnnotation(org.springframework.stereotype.Controller.class);

        // Alternatively, if you use @RestController instead of @Controller
        // Map<String, Object> controllers = applicationContext.getBeansWithAnnotation(org.springframework.web.bind.annotation.RestController.class);
        for (Object controller : controllers.values()) {
            // We only verify controllers in our base package
            if(controller.getClass().getPackageName().startsWith("com.example.trivia.api")) {
                assertTrue(controller instanceof BaseRestController,
                        () -> "The controller " + controller.getClass().getName() + " do not extend from BaseRestController");
            }
        }
    }

}