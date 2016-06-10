package io.tankertux;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

@Component
public class EventEmitter {

    @Autowired
    RabbitTemplate template;
    @Autowired
    TankertuxEventFactory factory;

    @Scheduled(fixedRate = 5000L)
    public void emit() throws Exception {
        template.convertAndSend("event.queue", factory.getObject());
        System.out.println("Message sent");
    }


}
