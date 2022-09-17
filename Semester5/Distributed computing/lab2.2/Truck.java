package lab2_2;

public class Truck {

    private int numberOfGoods;
    public int getNumberOfGoods(){
        return numberOfGoods;
    }
    private boolean isNewNumberOfGoods;

    Truck(){
        numberOfGoods =0;
        isNewNumberOfGoods =false;
    }
    public synchronized void putInside(){
        this.numberOfGoods++;
        isNewNumberOfGoods =true;
        notify();
    }

    public synchronized void waitForNewItem(){
        while(!isNewNumberOfGoods){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        isNewNumberOfGoods =false;
        notify();
    }
}