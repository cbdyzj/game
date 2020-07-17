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
        var headerLabel = new JLabel("", JLabel.CENTER);
        this.statusLabel = new JLabel("", JLabel.CENTER);
        this.statusLabel.setSize(350, 100);
        // controlPanel
        var controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        // main frame
        this.window = new JFrame("Java SWING Examples");
        this.window.setSize(400, 400);
        this.window.addWindowListener(this.onClose());
        this.window.setLayout(new GridLayout(3, 1));

        this.window.add(headerLabel);
        this.window.add(controlPanel);
        this.window.add(this.statusLabel);

        // okButton
        var okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        okButton.addActionListener(ev -> this.statusLabel.setText("Ok Button clicked."));
        controlPanel.add(okButton);
        // submitButton
        var submitButton = new JButton("Submit");
        submitButton.setActionCommand("Submit");
        submitButton.addActionListener(ev -> this.statusLabel.setText("Submit Button clicked."));
        controlPanel.add(submitButton);
        // cancelButton
        var cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(ev -> this.statusLabel.setText("Cancel Button clicked."));
        controlPanel.add(cancelButton);

        headerLabel.setText("Control in action: Button");
    }

    /**
     * show GUI
     */
    public void createAndShowGui() {
        this.createGui();
        this.window.setVisible(true);
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
