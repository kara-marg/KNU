package lab2_2;

public class Main {

    public static void main(String[] args) {

        Base base = new Base(10);
        Truck truck = new Truck();
        Man1 man1 = new Man1(base);
        Man2 man2 = new Man2(man1, truck);
        Man3 man3 = new Man3(man2, truck);
        man1.start();
        man2.start();
        man3.start();
    }
}
