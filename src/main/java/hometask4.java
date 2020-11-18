import javafx.concurrent.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class hometask4 {

    public static void main(String[] args) {

        ABCwriter Awriter = new ABCwriter("A", "B");
        ABCwriter Bwriter = new ABCwriter("B", "C");
        ABCwriter Cwriter = new ABCwriter("C", "A");


        Awriter.start();
        Bwriter.start();
        Cwriter.start();

        try {
            Awriter.join();
            Bwriter.join();
            Cwriter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("1 task is Ended");

        System.out.println("MFU task");

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        System.out.println("Enter operation: (p - print, s - scan, x - xerox)");
        while(true){
            try {
                input = bf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (input.equals("p")){

            }
            switch(input) {
                case "p":
                case "s":
                case "x":
                    new MFU.MFUaction(input).start(); break;
                default: System.out.println("неизвестная операция");
            }
        }
    }
}


