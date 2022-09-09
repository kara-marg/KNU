package main.java.ua.university.lab1_2;

public class JSemaphore {
    private int isReversed = 0;// 0 - зайнятий, 1 - вільний

    public boolean reserve() {
        if (isReversed == 1) {
            return false;
        } else {
            this.isReversed = 1;
            return true;
        }
    }

    public void makeFree() {
        isReversed = 0;
    }



}
