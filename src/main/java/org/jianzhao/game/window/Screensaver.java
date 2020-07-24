package org.jianzhao.game.window;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;

import static java.awt.Image.SCALE_SMOOTH;

/**
 * 屏保
 */
@Slf4j
public class Screensaver {

    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 440;

    public static final int INTERVAL = 20;

    private boolean closed = false;

    private JFrame window;
    private ScreensaverCanvas canvas;

    private void createGui() {
        this.window = new JFrame();
        this.window.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.window.setLocationRelativeTo(null);
        this.window.addWindowListener(this.onClose());
        this.canvas = new ScreensaverCanvas();
        this.window.add(this.canvas);
    }

    public void createAndShowGui() {
        this.createGui();
        this.window.setVisible(true);
        this.loop();
    }

    public void loop() {
        var timer = new Timer(INTERVAL, ev -> this.canvas.move());
        timer.start();
    }

    @SneakyThrows
    private static Image getImage(String path) {
        var imageFile = ResourceUtils.getFile(path);
        var imageBytes = StreamUtils.copyToByteArray(new FileInputStream(imageFile));
        return Toolkit.getDefaultToolkit().createImage(imageBytes);
    }

    /**
     * handle close
     */
    private WindowListener onClose() {
        return new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                Screensaver.this.closed = true;
                System.exit(0);
            }
        };
    }

    /**
     * 屏保画布
     */
    class ScreensaverCanvas extends Canvas {

        private static final int RIGHT_DOWN = 1;
        private static final int RIGHT_UP = 2;
        private static final int LEFT_DOWN = 3;
        private static final int LEFT_UP = 4;

        private static final int STEP = 1;

        private final Image backgroundImage;
        private final Image signImage;
        private static final int SIGN_IMAGE_WIDTH = 80;
        private static final int SIGN_IMAGE_HEIGHT = 50;

        public ScreensaverCanvas() {
            this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            var image = getImage("classpath:images/jdb.jpeg");
            this.signImage = image.getScaledInstance(SIGN_IMAGE_WIDTH, SIGN_IMAGE_HEIGHT, SCALE_SMOOTH);
            image = getImage("classpath:images/ybsbny.png");
            this.backgroundImage = image.getScaledInstance(DEFAULT_WIDTH, DEFAULT_HEIGHT, SCALE_SMOOTH);
        }

        private int x = 0;
        private int y = 0;

        private int direction = RIGHT_DOWN;

        @Override
        public void paint(Graphics g) {
            var g2d = (Graphics2D) g;
            g2d.drawImage(this.backgroundImage, 0, 0, null);
            g2d.drawImage(this.signImage, this.x, this.y, null);
        }

        public void move() {
            switch (this.direction) {
                case RIGHT_DOWN -> {
                    this.x += STEP;
                    this.y += STEP;
                }
                case RIGHT_UP -> {
                    this.x += STEP;
                    this.y -= STEP;
                }
                case LEFT_DOWN -> {
                    this.x -= STEP;
                    this.y += STEP;
                }
                case LEFT_UP -> {
                    this.x -= STEP;
                    this.y -= STEP;
                }
            }
            this.checkDirection();
            this.repaint();
        }

        public void checkDirection() {
            var window = Screensaver.this.window;
            int left = 0;
            int top = 0;
            // 调整，贴图，边框
            int right = window.getWidth() - SIGN_IMAGE_WIDTH - window.getInsets().left;
            // 调整，贴图，边框
            int bottom = window.getHeight() - SIGN_IMAGE_HEIGHT - window.getInsets().top;
            switch (this.direction) {
                case RIGHT_DOWN -> {
                    if (this.x > right && this.y > bottom) {
                        this.direction = LEFT_UP;
                        this.x -= STEP * 2;
                        this.y -= STEP * 2;
                    } else if (this.x > right) {
                        this.direction = LEFT_DOWN;
                        this.x -= STEP * 2;
                    } else if (this.y > bottom) {
                        this.direction = RIGHT_UP;
                        this.y -= STEP * 2;
                    }
                }
                case RIGHT_UP -> {
                    if (this.x > right && this.y < top) {
                        this.direction = LEFT_DOWN;
                        this.x -= STEP * 2;
                        this.y += STEP * 2;
                    } else if (this.x > right) {
                        this.direction = LEFT_UP;
                        this.x -= STEP * 2;
                    } else if (this.y < top) {
                        this.direction = RIGHT_DOWN;
                        this.y += STEP * 2;
                    }
                }
                case LEFT_DOWN -> {
                    if (this.x < left && this.y > bottom) {
                        this.direction = RIGHT_UP;
                        this.x += STEP * 2;
                        this.y -= STEP * 2;
                    } else if (this.x < left) {
                        this.direction = RIGHT_DOWN;
                        this.x += STEP * 2;
                    } else if (this.y > bottom) {
                        this.direction = LEFT_UP;
                        this.y -= STEP * 2;
                    }
                }
                case LEFT_UP -> {
                    if (this.x < left && this.y < top) {
                        this.direction = RIGHT_DOWN;
                        this.x += STEP * 2;
                        this.y += STEP * 2;
                    } else if (this.x < left) {
                        this.direction = RIGHT_UP;
                        this.x += STEP * 2;
                    } else if (this.y < top) {
                        this.direction = LEFT_DOWN;
                        this.y += STEP * 2;
                    }
                }
            }
        }
    }
}
