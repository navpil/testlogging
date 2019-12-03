package io.github.navpil.testlogging;

import org.apache.log4j.Logger;

public class UsesLog4j {

    public void doSomething() {
        Logger logger = Logger.getLogger(getClass());
        logger.warn("I'm Log4j warning");
    }

}
