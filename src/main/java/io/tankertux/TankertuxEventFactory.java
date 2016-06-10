package io.tankertux;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class TankertuxEventFactory implements FactoryBean<TankertuxEvent> {
    private SecureRandom random = new SecureRandom();

    @Override
    public TankertuxEvent getObject() throws Exception {
        List<String> ids = new ArrayList<>();
        int iterations = numberUpToFive();
        for (int i = 0; i < iterations; i++) {
            ids.add(UUID.randomUUID().toString());
        }

        TankertuxEvent event = new TankertuxEvent();
        event.setName(UUID.randomUUID().toString());
        event.setLevel(numberUpToFive());
        event.setIds(ids);
        return event;
    }

    private int numberUpToFive() {
        return random.nextInt() % 5;
    }

    @Override
    public Class<?> getObjectType() {
        return TankertuxEvent.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
