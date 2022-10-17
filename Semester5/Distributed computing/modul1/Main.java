package modul1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ViewerWhoChangeNumberOfCustomersInQueue viewer=new ViewerWhoChangeNumberOfCustomersInQueue();
        Print p=new Print(viewer);
        CashRegister[] cashRegisters={
                new CashRegister(viewer, "CashRegister1"),
                new CashRegister(viewer, "CashRegister2"),
                new CashRegister(viewer, "CashRegister3")
        };
        for (CashRegister cashRegister: cashRegisters) {
            viewer.add(cashRegister);
            cashRegister.start();
        }
        p.start();
        for (int i = 0; i < 10; i++) {
             Thread.currentThread().sleep(100);
             viewer.findBestCashRegister().add(new Customer(i+" customer"));
        }

    }

}

class CashRegister extends Thread{
    private ViewerWhoChangeNumberOfCustomersInQueue viewer;
    private Queue<Customer> queue;
    public CashRegister(ViewerWhoChangeNumberOfCustomersInQueue viewer, String name){
        super(name);
        queue=new LinkedList<Customer>();
        this.viewer=viewer;
    }
    public void add(Customer customer){
        queue.add(customer);
    }
    public Customer pop(){
        return ((LinkedList<Customer>)queue).removeLast();
    }

    @Override
    public void run() {
        while(true){
            Customer customer = queue.peek();

            if (customer==null){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                try {
                    viewer.findCashRegistersWWhoseHaveMoreCustomers(this);
                    System.out.println(customer + " start");
                    sleep((int)(Math.random()*6000)+4000);
                    queue.poll();
                    System.out.println(customer+" left queue");


                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public int getNumberOfCustomers(){
        return queue.size();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}

class Customer{
    private String name;
    Customer(String name){
        this.name = name;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                '}';
    }
}

class ViewerWhoChangeNumberOfCustomersInQueue{
    private static Semaphore SEMAPHORE = new Semaphore(1, true);
    ArrayList<CashRegister> cashRegisters;
    public ViewerWhoChangeNumberOfCustomersInQueue(){
        cashRegisters=new ArrayList<>();
    }
    public void add(CashRegister cashRegister){
        cashRegisters.add(cashRegister);
    }
    public void findCashRegistersWWhoseHaveMoreCustomers(CashRegister cashRegister) throws InterruptedException {
        SEMAPHORE.acquire();

        for (CashRegister currentCashRegister : cashRegisters) {
           // System.out.println("\t\t"+currentCashRegister.getName()+" "+currentCashRegister.getNumberOfCustomers()+" "+ cashRegister.getNumberOfCustomers());
            while(currentCashRegister.getNumberOfCustomers()-cashRegister.getNumberOfCustomers()>1){
                Customer customer= currentCashRegister.pop();
                System.out.println(customer + " from "+currentCashRegister.getName() + " to "+cashRegister.getName());
                cashRegister.add(customer);
            }
        }
        SEMAPHORE.release();
    }
    public CashRegister findBestCashRegister(){
        CashRegister result = cashRegisters.get(0);
        for (CashRegister currentCashRegister : cashRegisters) {
            result=(result.getNumberOfCustomers()>currentCashRegister.getNumberOfCustomers())?currentCashRegister:result;
        }
        return result;
    }
    public void printCashRegisters(){
        System.out.println("\nCashRegister");
        for (CashRegister currentCashRegister : cashRegisters) {
            System.out.println(currentCashRegister.toString());
        }
        System.out.println();
    }
}
class Print extends Thread{
    private ViewerWhoChangeNumberOfCustomersInQueue viewer;

    public Print(ViewerWhoChangeNumberOfCustomersInQueue viewer){
        this.viewer=viewer;
    }

    @Override
    public void run() {
        while (true){
            viewer.printCashRegisters();
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
