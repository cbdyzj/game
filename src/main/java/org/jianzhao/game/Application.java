package org.jianzhao.game;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.Assert;

import javax.swing.*;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = new SpringApplicationBuilder().sources(Application.class).headless(false).run(args);
    }

    public static ApplicationContext context() {
        Assert.notNull(context, "Context must not be null");
        return context;
    }

    @Override
    public void run(String... args) {
        SwingUtilities.invokeLater(new Game()::start);
    }

}
