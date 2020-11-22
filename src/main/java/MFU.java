public class MFU {
    private static int printTaskId = 0;
    private static int scanTaskId = 0;
    private static int xeroxTaskIdId = 0;


    static final Object printerLock = new Object();
    static final Object scanLock = new Object();



    static void print(){
        synchronized (printerLock) {
            System.out.println("Для задания на печать " + printTaskId + " началась печать");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Задание на печать " + printTaskId + " выполнено");
            System.out.println("-----------------------------------------");
            printTaskId++;
        }
    }
    static void scan(){
        synchronized (scanLock) {
            System.out.println("Для задания на скан " + scanTaskId + " началось сканирование");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Задания на скан " + scanTaskId + " выполнено.");
            System.out.println("-----------------------------------------");
            scanTaskId++;
        }
    }
    static void xerox(){
        synchronized (printerLock) {
            System.out.println("Для задания на ксерокс " + xeroxTaskIdId + " началось ксерокопирование");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Задание на ксерокс " + xeroxTaskIdId + " выполнено");
            System.out.println("-----------------------------------------");
            xeroxTaskIdId++;
        }
    }

    public static class MFUaction extends Thread{
        String operation;
        MFUaction(String operation){
            this.operation = operation;
            switch(operation) {
                case "p":
                    System.out.println("Пришло задание на печать с номером " + printTaskId);

                    break;
                case "s": System.out.println("Пришло задание на скан с номером" + scanTaskId); break;
                case "x": System.out.println("Пришло задание на ксерокопирование с номером" + xeroxTaskIdId); break;
                default: System.out.println("неизвестная операция");
            }
        }
        @Override
        public void run(){
            if(operation.equals("p")){
                print();
            }
            if(operation.equals("s")){
                scan();
            }
            if(operation.equals("x")){
                xerox();
            }


        }
    }


}
