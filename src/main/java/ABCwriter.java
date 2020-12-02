public class ABCwriter extends Thread {
    private String currentLetter;
    private String nextLetter;
    private final static Object locker = new Object();
    static volatile String str = "C";
    public ABCwriter(String currentLetter, String nextLetter){//, Object locker){
        this.currentLetter = currentLetter;
        this.nextLetter = nextLetter;
        //this.locker = locker;
    }

    public void run(){
        synchronized (locker) {
            for (int i = 0; i < 5; i++) {
                while (!currentLetter.equals(str)) {
                    try {
                        locker.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(nextLetter);
                str = nextLetter;
                locker.notifyAll();
            }
        }
    }
}
