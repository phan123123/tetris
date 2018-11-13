package tetris2;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Tetris extends JFrame {

    JPanel mainPanel;

    public Tetris() {
        creatMenu();
    }

    public void creatMenu() {
        mainPanel = new JPanel();
        add(mainPanel);
        setResizable(false);
        setSize(300,200);
        setLayout(new FlowLayout());
        mainPanel.setLayout(new FlowLayout());
        JButton play = new JButton("Play");
        play.setBounds(100, 100, 100, 100);
        JButton exit = new JButton("Exit");
        exit.setBounds(100, 300, 100, 100);
        mainPanel.add(exit);
        mainPanel.add(play);
        setVisible(true);
        mainPanel.setVisible(true);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Gameplay gp = new Gameplay();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Tetris.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
