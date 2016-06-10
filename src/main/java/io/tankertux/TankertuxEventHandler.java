package io.tankertux;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TankertuxEventHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void handleMessage(TankertuxEvent event) {
        logger.info(event.toString());
    }
}
