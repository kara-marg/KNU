package lab2_2;

public class Man3 extends Thread {
    private static final String name = "Nechepurchuk";
    Truck truck;
    Man2 man2;
    Man3(Man2 man2, Truck truck) {
        this.man2 = man2;
        this.truck = truck;
    }

    @Override
    public void run() {
        while (!man2.hasFinished()) {
            truck.waitForNewItem();
            System.out.println(name + " counted " + truck.getNumberOfGoods() + " goods");
        }
    }
}