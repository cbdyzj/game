package org.jianzhao.game;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@Slf4j
public class Game {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public Game() {
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
        this.mainFrame = new JFrame("Java SWING Examples");
        this.mainFrame.setSize(400, 400);
        this.mainFrame.addWindowListener(this.onClose());
        this.mainFrame.setLayout(new GridLayout(3, 1));

        this.mainFrame.add(headerLabel);
        this.mainFrame.add(controlPanel);
        this.mainFrame.add(statusLabel);
        this.mainFrame.setVisible(true);
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
        this.mainFrame.setVisible(true);
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
