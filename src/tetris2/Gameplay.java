package tetris2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Gameplay extends JFrame {

    public Gameplay() throws InterruptedException {
        creatGameplay();
    }

    JPanel leftPanel = new JPanel();
    MyGame middlePanel = new MyGame();
    JPanel rightPanel = new JPanel();

    private void initializeComponents() throws InterruptedException {
        setSize(900, 480);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 3));
        setTitle("TERTRIS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        initLeftFrame();
        initMiddleFrame();
        initRightFrame();
        add(leftPanel);
        add(middlePanel);
        add(rightPanel);
        middlePanel.g1 = leftPanel.getGraphics();
    }

    private void initLeftFrame() {
        leftPanel.setSize(300, 450);
        leftPanel.setVisible(true);
        leftPanel.setBackground(Color.white);

    }

    private void initRightFrame() {
        rightPanel.setLayout(new GridLayout(3, 1));
        rightPanel.setSize(300, 450);
        JButton exit = new JButton("Exit");
        rightPanel.setVisible(true);
        rightPanel.setBackground(Color.white);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JButton buttonPlay = new JButton("Play");
        
        rightPanel.add(buttonPlay);
        JLabel lb = new JLabel();
        rightPanel.add(lb);
        
        rightPanel.add(exit);
        lb.setBounds(50, 200, 100, 100);
        buttonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = middlePanel.getStringHighSource();
                int i = middlePanel.getHighSource();
                lb.setText("High Source:     " + str + "     -     " + i);
                middlePanel.reset();
                middlePanel.run();
            }
        });
    }

    private void initMiddleFrame() {
    }

    public void creatGameplay() throws InterruptedException {
        initializeComponents();
    }

}
