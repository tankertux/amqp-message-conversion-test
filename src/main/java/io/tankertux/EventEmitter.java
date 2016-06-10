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

    @Scheduled(fixedRate = 500L)
    public void emit() throws Exception {
        template.convertAndSend("io.tankertux", factory.getObject());
        template.convertAndSend("org.tankertux", factory.getObject());
    }


}
