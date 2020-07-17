package org.jianzhao.game.window;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * 屏保
 */
@Slf4j
public class Screensaver {

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 300;

    public static final int INTERVAL = 25;

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
        new Thread(this::loop).start();
    }

    @SuppressWarnings("BusyWait")
    @SneakyThrows
    public void loop() {
        while (!this.closed) {
            Thread.sleep(INTERVAL);
            this.canvas.move();
        }
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

        public ScreensaverCanvas() {
            this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            this.setBackground(Color.white);
        }

        private int x = 0;
        private int y = 0;

        private int direction = RIGHT_DOWN;

        @Override
        public void paint(Graphics g) {
            var g2d = (Graphics2D) g;
            g2d.drawString("保护", this.x, this.y);
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
            int top = 11;                           // 调整，标题栏
            int right = window.getWidth() - 26;     // 调整，贴图
            int bottom = window.getHeight() - 22;   // 调整，贴图
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
