package lab2_2;

public class Man2 extends Thread {
    private static final String name = "Petrov";
    private Truck truck;
    private Man1 man1;
    private static final int timeForPackingItemInCar = 400;
    private boolean isEnd;

    Man2(Man1 man1, Truck car) {
        this.man1 = man1;
        this.truck = car;
        this.isEnd = false;
    }

    @Override
    public void run() {
        while (man1.getAGood()) {
            System.out.println(name + " begins pack a good into truck");
            try {
                Thread.sleep(timeForPackingItemInCar);
                truck.putInside();
            } catch (InterruptedException e) {
            }
        }
        isEnd = true;
    }

    public boolean hasFinished() {
        return isEnd;
    }

}