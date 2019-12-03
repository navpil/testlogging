package io.github.navpil.testlogging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UsesApacheCommons {

    public void doSomething() {
        Log log = LogFactory.getLog(getClass());
        log.warn("I'm Apache Commons Logging warning");
    }

}
