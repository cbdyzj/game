package org.jianzhao.game.window;

import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.TypeUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Screensaver {

    private JFrame window;
    private JTextField inputN;

    private Canvas canvas;
    int n = 10;
    int[] x = new int[n];
    int[] y = new int[n];

    public Screensaver() {
        this.initGui();
    }

    private void initGui() {
        this.window = new JFrame();
        this.inputN = new JTextField(6);
        this.canvas = new Canvas() {
            @Override
            public void paint(Graphics g) {
                Screensaver.this.paint(g);
            }
        };
        this.window.setSize(400, 300);
        this.window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.window.setLayout(new FlowLayout());
        this.window.add(new Label("请输入变数:"));
        this.window.add(this.inputN);
        var button = new JButton("确定");
        button.addActionListener(this::onClick);
        this.window.add(button);
        this.window.add(this.canvas);
    }

    public void onClick(ActionEvent ev) {
        int N = Integer.parseInt(this.inputN.getText());
        int[] x = new int[N];
        int[] y = new int[N];
        for (int i = 0; i < N; i++) {
            x[i] = (int) (Math.random() * 200);
            y[i] = (int) (Math.random() * 200);
        }
        this.setOval(x, y, N);
        this.canvas.repaint();

    }

    public void setOval(int[] x, int[] y, int n) {
        this.n = n;
        for (int i = 0; i < n; i++) {
            this.x[i] = x[i];
            this.y[i] = y[i];
        }
    }

    public void createAndShowGui() {
        this.canvas.setSize(300, 200);
        this.canvas.setBackground(Color.white);
        this.window.setVisible(true);
    }

    public void paint(Graphics g) {
        var g2d = (Graphics2D) g;
        g2d.drawPolygon(this.x, this.y, this.n);
    }
}
