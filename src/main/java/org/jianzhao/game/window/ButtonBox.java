package org.jianzhao.game.window;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@Slf4j
public class ButtonBox {

    private JFrame window;
    private JLabel statusLabel;

    private void createGui() {
        // header label
        var headerLabel = new JLabel("Control in action: Button", JLabel.CENTER);
        // controlPanel
        var controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(this.createButton("Submit"));
        controlPanel.add(this.createButton("OK"));
        controlPanel.add(this.createButton("Cancel"));
        // status label
        this.statusLabel = new JLabel("", JLabel.CENTER);
        this.statusLabel.setSize(350, 100);
        // main frame
        this.window = new JFrame("Java SWING Examples");
        this.window.add(headerLabel);
        this.window.add(controlPanel);
        this.window.add(this.statusLabel);
        this.window.setLocationRelativeTo(null);
        this.window.setSize(400, 400);
        this.window.addWindowListener(this.onClose());
        this.window.setLayout(new GridLayout(3, 1));
    }

    /**
     * show GUI
     */
    public void createAndShowGui() {
        this.createGui();
        this.window.setVisible(true);
    }

    private JButton createButton(String name) {
        var button = new JButton(name);
        button.setActionCommand(name);
        button.addActionListener(ev -> this.statusLabel.setText(name + " Button clicked."));
        return button;
    }

    /**
     * handle close
     */
    private WindowListener onClose() {
        return new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        };
    }

}
