package io.github.navpil.testlogging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UsesLog4j2 {

    public void doSomething() {
        Logger logger = LogManager.getLogger(UsesLog4j2.class);
        logger.warn("I'm Log4j2 Warning");
    }

}
