package io.github.navpil.testlogging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsesSlf4j {

    public void doSomething() {
        Logger logger = LoggerFactory.getLogger(UsesSlf4j.class);
        logger.warn("I'm SLF4j warning");
    }
}
