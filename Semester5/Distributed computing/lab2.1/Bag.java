package lab2_1;

import java.util.ArrayDeque;
import java.util.Queue;

public class Bag {
    Queue<Integer> listOfSection;
    Bag(int numberOfRows) {
        listOfSection=new ArrayDeque<>();
        for (int i = 0; i < numberOfRows; i++) {
            listOfSection.add(i);
        }
    }
    public int getUncheckedRow(Bee bee) {
        if (bee != null){
            listOfSection.add(bee.getRowForSearching());
        }
        return listOfSection.poll();
    }
}
