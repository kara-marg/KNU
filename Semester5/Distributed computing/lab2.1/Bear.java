package lab2_1;

public class Bear extends Thread {
    public boolean isNotDead;
    Field field;
    int x_speed, y_speed, x, y;
    private static final int timeWaiting = 600;
    private static final int limit = 2;
    private static final int startPos = 11;

    Bear(Field field) {
        isNotDead = true;
        x_speed = y_speed = -1;
        this.field = field;
        x = y = startPos;
    }

    public void kill() {
        this.isNotDead = false;
    }

    @Override
    public void run() {
        while (isNotDead) {
            if (field.checkIfPointIsReserved(x, y)) {
                isNotDead = false;
                return;
            }
            field.stepIntoPoint(x, y);
            try {
                Thread.sleep((int) (timeWaiting * Math.random() * (limit + 1)));
            } catch (Exception e) {
            }
            field.stepOutPoint(x, y);
            makeStep();
        }
        System.out.println(x + " " + y);
    }

    public void makeStep() {
        if (x + x_speed < 0 || x + x_speed >= field.getWidth()) {
            x_speed *= -1; //change way
        }
        if (y + y_speed < 0 || y + y_speed >= field.getHeight()) {
            y_speed *= -1; //change way
        }

        x += x_speed;
        y += y_speed;

    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
