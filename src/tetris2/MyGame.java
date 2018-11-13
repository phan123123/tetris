/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris2;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class MyGame extends JPanel implements KeyListener {

    int[][] matrix = new int[12][18];
    int[][] tempMatrix = new int[12][18];
    int source;
    int action;
    Puzzle puz;
    Puzzle tempPuzzle;
    Graphics g1;
    int speed;

    void setSpeed(){
        this.speed=this.speed-source*25;
        if(this.speed<=250) this.speed = 250;
    }
    
    public MyGame() {
        action = 0;
        speed =750;
        this.source = 0;
        setBounds(300, 0, 300, 450);
        setBackground(Color.black);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 18; j++) {
                matrix[i][j] = 0;
            }
        }
        tempPuzzle = new Puzzle();
    }

    public void setTempMatrix(Puzzle x) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 18; j++) {
                tempMatrix[i][j] = matrix[i][j];
            }
        }
        if (check(x)) {
            for (Pair a : x.point) {
                tempMatrix[a.first][a.second] = 1;
            }
        }
    }

    public boolean end() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 3; j++) {

                if (matrix[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(Puzzle x) {

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 18; j++) {
                for (Pair a : x.point) {
                    if ((a.first < 0 || a.first > 11) || (a.second < 0 || a.second > 17)) {
                        return false;
                    }
                    if (matrix[a.first][a.second] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void goal(int k) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j <= k; j++) {
                if (matrix[i][j] == 1) {
                    if (k > j) {
                        for (int m = k; m > j; m--) {
                            matrix[i][m] = matrix[i][m - 1];
                        }
                    }
                    matrix[i][j] = 0;
                    break;
                }
            }
        }
    }

    public void processing() {
        int i, j, m = 0;
        int temp;
        for (i = 0; i < 18; i++) {
            for (j = 0; j < 12; j++) {
                matrix[j][i] = tempMatrix[j][i];
            }
        }
        for (i = 17; i > 0; i--) {
            temp = 0;
            for (j = 0; j < 12; j++) {
                temp += matrix[j][i];
            }
            if (temp == 12) {
                goal(i);
                i++;
                source++;
            }
        }
        for (i = 0; i < 12; i++) {
            for (j = 0; j < 18; j++) {
                tempMatrix[i][j] = matrix[i][j];
            }
        }

    }

    private void draw(Graphics g, Graphics g1) {
        g.setColor(Color.red);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 18; j++) {
                if (tempMatrix[i][j] == 1) {
                    g.fillRect(i * 25, j * 25, 24, 24);
                }
            }
        }
        g1.setColor(Color.blue);
        g1.drawString("SOURCE: ", 5 * 25 + 15, 275);
        g1.drawString("" + source * 10, 5 * 25 + 15, 300);
        g1.drawString("NEXT: ", 50, 50);
        for (int i = 0; i < 4; i++) {
            g1.fillRect(tempPuzzle.point[i].first * 25 + 50, tempPuzzle.point[i].second * 25 + 100, 24, 24);
        }
    }

    private void clear(Graphics g, Graphics g1) {
        g.setColor(Color.black);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 18; j++) {
                g.fillRect(i * 25, j * 25, 25, 25);
            }
        }
        g1.setColor(Color.white);
        g1.fillRect(0, 0, 275, 250);
        g1.fillRect(4 * 25, 250, 100, 100);
    }

    void drawEnd(Graphics g, Graphics g1) {
        clear(g, g1);
        g1.setColor(Color.blue);
        g1.drawString("SOURCE: ", 5 * 25 + 15, 275);
        g1.drawString("" + source * 10, 5 * 25 + 15, 300);
        g.setColor(Color.white);
        g.drawString("GAME OVER", 115, 225);
    }

    public void key(Puzzle puz) throws InterruptedException {
        Graphics g = getGraphics();
        if (action == 1) {
            rotate(puz);
            if (check(puz) == false) {
                puz.load();
            }
            setTempMatrix(puz);
            clear(g, g1);
            draw(g, g1);
            Thread.sleep(175);
        }
        while (action == 2) {
            turnLeft(puz);
            if (check(puz) == false) {
                puz.load();
            }
            setTempMatrix(puz);
            clear(g, g1);
            draw(g, g1);
            Thread.sleep(100);
        }
        while (action == 3) {
            turnRight(puz);
            if (check(puz) == false) {
                puz.load();
            }
            setTempMatrix(puz);
            clear(g, g1);
            draw(g, g1);
            Thread.sleep(100);
        }
        while (action == 4) {
            runDown(puz);
            if (check(puz) == false) {
                puz.load();
            }
            setTempMatrix(puz);
            clear(g, g1);
            draw(g, g1);
            Thread.sleep(85);
        }
    }

    private void down() throws InterruptedException {

        Graphics g = getGraphics();
        while (end() == false) {
            puz = new Puzzle();
            puz.i = tempPuzzle.i;
            puz.set(puz.i);
            tempPuzzle = new Puzzle();
            while (check(puz)) {
                runDown(puz);
                if (check(puz) == false) {
                    puz.load();
                    setTempMatrix(puz);
                    clear(g, g1);
                    draw(g, g1);
                    processing();
                    break;
                }
                setTempMatrix(puz);
                clear(g, g1);
                draw(g, g1);
                setSpeed();
                Thread.sleep(speed);
            }
        }
        if (end()) {
            Thread.interrupted();
            proEnd();
            drawEnd(g, g1);
        }
    }

    private void control() throws InterruptedException {
        while (true) {
            key(puz);
            Thread.sleep(100);
        }
    }
    
    Thread runningThread1,runningThread2;
    public void run() {
        if (runningThread1!=null && runningThread1.isAlive()) {
            runningThread1.interrupt();
        }
        if (runningThread2!=null && runningThread2.isAlive()) {
            runningThread2.interrupt();
        }
        runningThread1 = new Thread() {
            @Override
            public void run() {
                try {
                    down();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MyGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        runningThread2 = new Thread() {
            @Override
            public void run() {
                try {
                    if(end()){
                        interrupt();
                    }
                    control();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MyGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        runningThread1.start();
        runningThread2.start();
    }

    public void runDown(Puzzle puz) {
        puz.save();
        for (Pair x : puz.point) {
            x.second++;
        }
    }

    public void turnLeft(Puzzle puz) {
        puz.save();
        for (Pair x : puz.point) {
            x.first--;
        }
    }

    public void turnRight(Puzzle puz) {
        puz.save();
        for (Pair x : puz.point) {
            x.first++;
        }
    }

    public void rotate(Puzzle puz) {
        puz.save();
        if (puz.i == 1) {
            puz.i = 2;
            puz.set(puz.i);
            return;
        }
        if (puz.i == 2) {
            puz.i = 1;
            puz.set(puz.i);
            return;
        }
        if (puz.i == 3) {
            puz.i = 4;
            puz.set(puz.i);
            return;
        }
        if (puz.i == 4) {
            puz.i = 14;
            puz.set(puz.i);
            return;
        }
        if (puz.i == 14) {
            puz.i = 15;
            puz.set(puz.i);
            return;
        }
        if (puz.i == 15) {
            puz.i = 3;
            puz.set(puz.i);
            return;
        }
        if (puz.i == 6) {
            puz.i = 7;
            puz.set(puz.i);
            return;
        }
        if (puz.i == 7) {
            puz.i = 16;
            puz.set(puz.i);
            return;
        }
        if (puz.i == 16) {
            puz.i = 17;
            puz.set(puz.i);
            return;
        }
        if (puz.i == 17) {
            puz.i = 6;
            puz.set(puz.i);
            return;
        }
        if (puz.i == 8) {
            puz.i = 9;
            puz.set(puz.i);
            return;
        }
        if (puz.i == 9) {
            puz.i = 8;
            puz.set(puz.i);
            return;
        }
        if (puz.i == 10) {
            puz.i = 11;
            puz.set(puz.i);
            return;
        }
        if (puz.i == 11) {
            puz.i = 18;
            puz.set(puz.i);
            return;
        }
        if (puz.i == 18) {
            puz.i = 19;
            puz.set(puz.i);
            return;
        }
        if (puz.i == 19) {
            puz.i = 10;
            puz.set(puz.i);
            return;
        }
        if (puz.i == 12) {
            puz.i = 13;
            puz.set(puz.i);
            return;
        }
        if (puz.i == 13) {
            puz.i = 12;
            puz.set(puz.i);
            return;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("1");
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            action = 1;
        }
        if (key == KeyEvent.VK_LEFT) {
            action = 2;
        }
        if (key == KeyEvent.VK_RIGHT) {
            action = 3;
        }
        if (key == KeyEvent.VK_DOWN) {
            action = 4;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        action = 0;
    }

    void reset() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 18; j++) {
                matrix[i][j] = 0;
                tempMatrix[i][j] = 0;
            }
        }
        this.source = 0;
        Graphics g = getGraphics();
        clear(g, g1);
    }

    int getHighSource() {
        int i = 0;
        try {
            FileInputStream fis = new FileInputStream("E:\\netbean\\TETRIS2\\highsource.txt");
            DataInputStream dis = new DataInputStream(fis);
            i = dis.readInt();
        } catch (Exception ex) {

        }
        return i;
    }

    String getStringHighSource() {
        try {
            FileInputStream fis = new FileInputStream("E:\\netbean\\TETRIS2\\name.txt");
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(bis));
            String str;
            str = br.readLine();
            fis.close();
            bis.close();
            br.close();
            return str;
        } catch (IOException ex) {
            Logger.getLogger(MyGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    void setHighSource(String str) {
        try {
            FileOutputStream fos = new FileOutputStream("E:\\netbean\\TETRIS2\\highsource.txt");
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeInt(source * 10);
            dos.close();
            fos.close();
            File f1 = new File("E:\\netbean\\TETRIS2\\name.txt");
            FileWriter fw1 = new FileWriter(f1);
            fw1.write(str);
            fw1.close();
        } catch (IOException ex) {
            Logger.getLogger(MyGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void proEnd() {
        if ((source * 10) > getHighSource()) {
            JFrame inName = new JFrame("Enter your name");
            JTextField tf = new JTextField("Enter your name: ",20);
            JButton bt = new JButton("OK");
            inName.setBounds(450, 300, 250 , 100);
            inName.setLayout(new GridLayout(2,1));
            inName.add(tf);
            inName.add(bt);
            inName.setVisible(true);
            bt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String str = tf.getText();
                    setHighSource(str);
                    inName.dispose();
                }
            });
        }
    }
}
