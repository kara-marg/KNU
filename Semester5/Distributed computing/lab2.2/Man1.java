package lab2_2;

public class Man1 extends Thread {
    private static final String name = "Ivanov";
    private Base base;
    private static final int timeForCarryingItem = 2000;
    private static final int timeForReturningToStorage = 400;
    private boolean hasFinished;
    private boolean hasComeToMan2;
    Man1(Base base) {
        this.base = base;
    }

    @Override
    public synchronized void run() {
        while (true) {
            if (base.getItem()) {
                System.out.println(name + " stole a good  and moves to Petrov");
                try {
                    Thread.sleep(timeForCarryingItem);
                } catch (InterruptedException e) {
                }
                System.out.println(name +" wait for Petrov");
                hasComeToMan2=true;
                notify();
                while (hasComeToMan2){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Ivanov moves to new item");
                try {
                    Thread.sleep(timeForReturningToStorage);
                } catch (InterruptedException e) {
                }
            } else {
                break;
            }
        }
        hasFinished = true;
    }

    public synchronized boolean getAGood() {
        while (!hasComeToMan2) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        hasComeToMan2 = false;
        notify();
        return true;
    }
}