package lab2_2;

public class Base {
    private int numberOfMunition;
    Base(int numberOfMunition){
        this.numberOfMunition=numberOfMunition;
    }
    public boolean getItem(){
        if (numberOfMunition>0){
            numberOfMunition--;
            return true;
        }
        return false;
    }
}
