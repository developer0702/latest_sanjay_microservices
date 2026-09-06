package com.serviceRegistry.department_registry.service;


import com.serviceRegistry.department_registry.event.UserCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventConsumer {

    @KafkaListener(
            topics = "user-created",
            groupId = "department-service-group"
    )
    public void consume(UserCreatedEvent event) {

        System.out.println("=================================");
        System.out.println("User event received");
        System.out.println("User ID   : " + event.getUserId());
        System.out.println("User Name : " + event.getUserName());
        System.out.println("Email     : " + event.getEmail());
        System.out.println("=================================");
    }
}
