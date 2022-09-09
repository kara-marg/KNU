package main.java.ua.university.lab1_2;

import javax.swing.*;

public class Main {
    private static final int step = 2;

    private static boolean isPlusThreadAlive = true;
    private static boolean isMinusThreadAlive = true;

    private static Thread plusThread;
    private static Thread minusThread;
    private static JSemaphore jSem;

    public static void main(String[] args) {
        jSem = new JSemaphore();
        JFrame window = new JFrame("lab1.2"); 
        window.setSize(400, 500); 

        JButton buttonStart1 = new JButton("Start1");
        JButton buttonStart2 = new JButton("Start2");
        JButton buttonStop1 = new JButton("Stop1");
        JButton buttonStop2 = new JButton("Stop2");
        SpinnerModel spinner1 = new SpinnerNumberModel(0, 0, 5, 1);
        SpinnerModel spinner2 = new SpinnerNumberModel(0, 0, 5, 1);
        JSpinner plus = new JSpinner(spinner1);
        JSpinner minus = new JSpinner(spinner2);
        JSlider slider = new JSlider(0, 100, 50);

        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(10);

        window.add(plus);
        window.add(minus);
        window.add(buttonStart1);
        window.add(buttonStart2);
        window.add(buttonStop1);
        window.add(buttonStop2);
        window.add(slider);

        buttonStart1.setBounds(75, 300, 100, 40);
        buttonStart2.setBounds(225, 300, 100, 40);
        buttonStop1.setBounds(75, 400, 100, 40);
        buttonStop2.setBounds(225, 400, 100, 40);


        plus.setBounds(75, 185, 100, 45);
        minus.setBounds(225, 185, 100, 45);

        slider.setBounds(50, 100, 300, 60);


        Runnable rPlus = () -> {
            isPlusThreadAlive = true;
            while (isPlusThreadAlive) {
                System.out.println(Thread.interrupted());
                synchronized (slider) {

                    int newPos = slider.getValue() + step;
                    if (newPos > 90)
                        slider.setValue(90);
                    else
                        slider.setValue(newPos);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("Thread error1");
                    }
                }
            }
            isPlusThreadAlive = true;
            jSem.makeFree();
        };
        Runnable rMinus = () -> {
            isMinusThreadAlive = true;
            while (isMinusThreadAlive) {

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
            jSem.makeFree();
            isMinusThreadAlive = true;
        };


        buttonStart1.addActionListener(event -> {
            if (jSem.reserve()) {
                plusThread = new Thread(rPlus);
                plusThread.start();
                buttonStart1.setEnabled(false);
                buttonStop2.setEnabled(false);
                plusThread.setPriority(5);
                spinner1.setValue(5);
            } else {
                JOptionPane.showMessageDialog(null, "Зайнято потоком");
            }
        });
        buttonStart2.addActionListener(event -> {
            if (jSem.reserve()) {
                minusThread = new Thread(rMinus);
                minusThread.start();
                buttonStart2.setEnabled(false);
                buttonStop1.setEnabled(false);
                plusThread.setPriority(0);
                spinner1.setValue(0);
            } else {
                JOptionPane.showMessageDialog(null, "Зайнято потоком");
            }
        });

        buttonStop1.addActionListener(event -> {
            isPlusThreadAlive = false;
            buttonStart1.setEnabled(true);
            buttonStart2.setEnabled(true);
            buttonStop2.setEnabled(true);
        });
        buttonStop2.addActionListener(event -> {
            isMinusThreadAlive = false;
            buttonStart1.setEnabled(true);
            buttonStart2.setEnabled(true);
            buttonStop1.setEnabled(true);
        });
        plus.addChangeListener(e -> {
            plusThread.setPriority(Integer.parseInt(plus.getValue().toString()));
        });
        minus.addChangeListener(e -> {
            minusThread.setPriority(Integer.parseInt(minus.getValue().toString()));
        });

        window.setLayout(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
