package main.java.ua.university.lab1_1;

import javax.swing.*;

public class Main {
    private static final int step = 2;
    private static Thread plusThread;
    private static Thread minusThread;
    private static volatile JSlider slider;
    private static boolean isAlive = true;

    public static void main(String[] args) {
        JFrame window = new JFrame("lab1.1"); 
        window.setSize(400, 500); 

        JButton buttonStart = new JButton("Start!");
        SpinnerModel spinner1 = new SpinnerNumberModel(1, 1, 5, 1);
        SpinnerModel spinner2 = new SpinnerNumberModel(1, 1, 5, 1);
        JSpinner plus = new JSpinner(spinner1);
        JSpinner minus = new JSpinner(spinner2);
        slider = new JSlider(0, 100, 50);

        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(10);

        window.add(plus);
        window.add(minus);
        window.add(buttonStart);
        window.add(slider);

        plus.setBounds(75, 185, 100, 45);
        minus.setBounds(225, 185, 100, 45);
        buttonStart.setBounds(100, 300, 200, 40);
        slider.setBounds(50, 100, 300, 60);
        Runnable rPlus = () -> {

            while (isAlive) {
                synchronized (slider) {

                    int newPos = slider.getValue() + step;
                    if (newPos > 90)
                        slider.setValue(90);
                    else
                        slider.setValue(newPos);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("Thread error");
                    }
                }
            }
            isAlive = true;
        };
        Runnable rMinus = () -> {
            while (isAlive) {

                synchronized (slider) {

                    int newPos = slider.getValue() - step;
                    if (newPos < 10)
                        slider.setValue(10);
                    else
                        slider.setValue(newPos);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("Thread error");
                    }
                }
            }
            isAlive = true;
        };

        plus.addChangeListener(e -> {
            isAlive = false;
            startThreads(rPlus, rMinus, Integer.parseInt(plus.getValue().toString()), Integer.parseInt(minus.getValue().toString()));

        });
        minus.addChangeListener(e -> {
            isAlive = false;
            startThreads(rPlus, rMinus, Integer.parseInt(plus.getValue().toString()), Integer.parseInt(minus.getValue().toString()));

        });
        buttonStart.addActionListener(event -> {
            buttonStart.setEnabled(false);
            startThreads(rPlus, rMinus, Thread.MIN_PRIORITY, Thread.MIN_PRIORITY);
        });
     
        window.setLayout(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void startThreads(Runnable r1, Runnable r2, int p1, int p2) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Thread error");
        }
        plusThread = new Thread(r1);
        minusThread = new Thread(r2);
        if (p1 > p2) {
            plusThread.setPriority(Thread.MAX_PRIORITY);
            minusThread.setPriority(Thread.MIN_PRIORITY);
        } else {
            plusThread.setPriority(Thread.MIN_PRIORITY);
            minusThread.setPriority(Thread.MAX_PRIORITY);
        }
        plusThread.start();
        minusThread.start();
        System.out.println(plusThread.getPriority() + " " + minusThread.getPriority());
    }
}
