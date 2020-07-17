package org.jianzhao.game.window;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@Slf4j
public class ButtonBox {

    private JFrame window;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public ButtonBox() {
        this.initGui();
    }

    private void initGui() {
        // header label
        this.headerLabel = new JLabel("", JLabel.CENTER);
        this.statusLabel = new JLabel("", JLabel.CENTER);
        this.statusLabel.setSize(350, 100);
        // controlPanel
        this.controlPanel = new JPanel();
        this.controlPanel.setLayout(new FlowLayout());

        // main frame
        this.window = new JFrame("Java SWING Examples");
        this.window.setSize(400, 400);
        this.window.addWindowListener(this.onClose());
        this.window.setLayout(new GridLayout(3, 1));

        this.window.add(this.headerLabel);
        this.window.add(this.controlPanel);
        this.window.add(this.statusLabel);
    }


    /**
     * show GUI
     */
    public void createAndShowGui() {
        // okButton
        var okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        okButton.addActionListener(ev -> this.statusLabel.setText("Ok Button clicked."));
        this.controlPanel.add(okButton);
        // submitButton
        var submitButton = new JButton("Submit");
        submitButton.setActionCommand("Submit");
        submitButton.addActionListener(ev -> this.statusLabel.setText("Submit Button clicked."));
        this.controlPanel.add(submitButton);
        // cancelButton
        var cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        cancelButton.addActionListener(ev -> this.statusLabel.setText("Cancel Button clicked."));
        this.controlPanel.add(cancelButton);

        this.headerLabel.setText("Control in action: Button");
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
