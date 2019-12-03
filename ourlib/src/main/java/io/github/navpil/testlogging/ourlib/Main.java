package io.github.navpil.testlogging.ourlib;

import io.github.navpil.testlogging.UsesApacheCommons;
import io.github.navpil.testlogging.UsesJul;
import io.github.navpil.testlogging.UsesLog4j;
import io.github.navpil.testlogging.UsesLog4j2;
import io.github.navpil.testlogging.UsesSlf4j;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.LogManager;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        //This is only needed for JUL configuration
        ClassLoader classLoader = Main.class.getClassLoader();
        if (classLoader.getResourceAsStream(".use-log4j2-jul") != null) {
            System.out.println("Will setup JUL to Log4j2");
            System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
        } else if (classLoader.getResourceAsStream(".use-slf4j-jul") != null) {
            System.out.println("Will setup JUL to Slf4j2");
            reflectivelyInstall();
        } else if (classLoader.getResourceAsStream("jul.properties") != null) {
            InputStream resourceAsStream = classLoader.getResourceAsStream("jul.properties");
            LogManager.getLogManager().readConfiguration(resourceAsStream);
        }

        callLog("JUL", new UsesJul()::doSomething);
        callLog("Log4j", new UsesLog4j()::doSomething);
        callLog("Log4j2", new UsesLog4j2()::doSomething);
        callLog("JCL", new UsesApacheCommons()::doSomething);
        callLog("Slf4j", new UsesSlf4j()::doSomething);
    }

    private static void reflectivelyInstall() {
        //I make this call reflectively only because this is an example project and SLF4JBridgeHandler is not
        // always available in classpath. Otherwise I would simply call the following line.
        //SLF4JBridgeHandler.install();
        try {
            Class<?> aClass = Class.forName("org.slf4j.bridge.SLF4JBridgeHandler");
            Method install = aClass.getMethod("install");
            install.invoke(null);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private static void callLog(String logName, Runnable runnable) throws InterruptedException {
        int minuses = 20 - logName.length();
        int i = minuses / 2;
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < i; j++) {
            stringBuilder.append("-");
        }
        stringBuilder.append(logName);
        for (int j = 0; j < (minuses - i); j++) {
            stringBuilder.append("-");
        }
        System.out.println(stringBuilder.toString());
        Thread.sleep(100);
        runnable.run();
        Thread.sleep(100);

    }


}
