package org.loomdev.launcher;

import java.lang.instrument.Instrumentation;
import java.util.jar.JarFile;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

class Agent {

    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation instrumentation) {
        Agent.instrumentation = instrumentation;
    }

    public static void agentmain(String args, Instrumentation instrumentation) {
        Agent.instrumentation = instrumentation;
    }

    static void addToClassPath(File jar) {
        if (instrumentation == null) {
            try {
            System.err.println("Please add -javaagent:" + new File(Agent.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getName());
            System.exit(1);
            } catch (URISyntaxException | NullPointerException error) {
                System.err.println("Please add -javaagent:<loom_launcher>");
            }
        }
        try {
            instrumentation.appendToSystemClassLoaderSearch(new JarFile(jar));
        } catch (IOException error) {
            System.err.println("Could not add Loom to classpath");
            error.printStackTrace();
            System.exit(1);
        }
    }

}

