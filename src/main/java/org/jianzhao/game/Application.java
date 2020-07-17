package org.jianzhao.game;

import org.jianzhao.game.window.ButtonBox;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(Application.class).headless(false).run(args);
    }

    @Override
    public void run(String... args) {
        new Game().createAndShowGui();
    }
}
