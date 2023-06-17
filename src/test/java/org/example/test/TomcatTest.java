package org.example.test;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.example.WebApplicationServerLauncher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class TomcatTest {
    private static final Logger logger = LoggerFactory.getLogger(WebApplicationServerLauncher.class);
    private final Tomcat tomcat;
    private final Callback callback;

    public void run() throws LifecycleException, IOException {
        this.tomcat.start();
        this.callback.call();
        this.tomcat.stop();
        this.tomcat.destroy();
    }

    public TomcatTest(Callback callback) {
        // 내장 톰캣
        String webappDirLocation = "webapp/";
        this.tomcat = new Tomcat();
        this.callback = callback;
        tomcat.setPort(8090);

        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        logger.info("configuring app with basedir: {}", new File("./" + webappDirLocation).getAbsolutePath());
    }

    @FunctionalInterface
    public interface Callback {
        void call() throws IOException;
    }
}
