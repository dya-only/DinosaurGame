package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class DinoGame extends JPanel implements KeyListener, Runnable {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int yPos = -50;
    int jumpVelocity = 13;
    boolean isJumping = false;
    int barrierX = 0;
    int barrierSpeed = 5;

    Image dinoImage;
    Image floorImage;
    Image barrier1Image;

    public DinoGame() {
        this.setFocusable(true);
        this.addKeyListener(this);

        dinoImage = new ImageIcon("assets/dinosaur.png").getImage();
        floorImage = new ImageIcon("assets/floor.png").getImage();
        barrier1Image = new ImageIcon("assets/barrier1.png").getImage();

        new Thread(this).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Player (Dinosaur)
        g.drawImage(dinoImage, 100, 300 - yPos, 40, 40, this);

        // Floor
        g.drawImage(floorImage, barrierX, 385, 800, 6, this);
        g.drawImage(floorImage, barrierX + 800, 385, 800, 6, this);

        // Barriers
        g.drawImage(barrier1Image, barrierX + 850, 340, 30, 50, this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (isJumping) {
                yPos += jumpVelocity;
                jumpVelocity -= 1;
                if (yPos <= -50) {
                    yPos = -50;
                    isJumping = false;
                    jumpVelocity = 13;
                }
            }

            barrierX -= barrierSpeed;
            if (barrierX < -(screenSize.getWidth() / 2 - 100)) barrierX = 0;

            this.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !isJumping) {
            isJumping = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800, 500);
        frame.add(new DinoGame());
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}