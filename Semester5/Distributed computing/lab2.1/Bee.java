package lab2_1;

public class Bee extends Thread {
    private int rowForSearching;
    private Field field;
    private Bear bear;
    private Bag bag;
    private static final int timeForStep = 100;
    private int x;

    Bee(Field field, Bear bear, Bag bag) {

        this.field = field;
        this.bear = bear;
        this.bag = bag;
        this.rowForSearching = bag.getUncheckedRow(null);;
    }

    @Override
    public void run() {
        while (bear.isNotDead) {
            for (int column = 0; column < field.getWidth(); column++) {

                x=column;
                if (!bear.isNotDead || field.checkIfPointIsReserved(column, rowForSearching)) {
                    bear.kill();
                    return;
                }
                field.stepIntoPoint(column, rowForSearching);
                try {
                    Thread.sleep((int) (timeForStep * (Math.random() * 3 +1)));
                } catch (Exception e) {
                }
                field.stepOutPoint(column, rowForSearching);
            }
            rowForSearching=bag.getUncheckedRow(this);
        }
    }


    public int getX() {
        return x;
    }

    public int getRowForSearching() {
        return rowForSearching;
    }
}