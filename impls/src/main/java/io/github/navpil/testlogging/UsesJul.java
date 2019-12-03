package io.github.navpil.testlogging;

import java.util.logging.Logger;

public class UsesJul {

    public void doSomething() {
        Logger logger = Logger.getLogger(getClass().getCanonicalName());
        logger.warning("I'm JUL warning");
    }

}
