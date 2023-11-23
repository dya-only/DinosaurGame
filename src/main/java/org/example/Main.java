package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class GameWindow extends JFrame {
    Toolkit imageTool = Toolkit.getDefaultToolkit();
    Image dinosaur = imageTool.getImage("assets/dinosaur.png");
    Image buffImg;
    Graphics buffG;

    double yPos = 300;

    public GameWindow() {
        setTitle("Dinosaur Game");
        setSize(854, 480);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    yPos -= 50;
                }
            }
        });

    }

    @Override
    public void paint(Graphics g) {
        buffImg = createImage(getWidth(), getHeight());
        buffG = buffImg.getGraphics();
        update(g);
    }

    @Override
    public void update(Graphics g) {
        buffG.clearRect(0, 0, 20, 20);
        buffG.drawImage(dinosaur, 100, (int) yPos, 50, 50, this);
        g.drawImage(buffImg, 0, 0, this);
        yPos += 0.2;

        if (yPos >= 300) yPos -= 0.2;

        repaint();
    }
}

public class Main {
    public static void main(String[] args) {
        new GameWindow();
    }
}