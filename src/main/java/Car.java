import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {
    public static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier startAndFinishLines;
    public static AtomicInteger isWinner = new AtomicInteger(0);
    public Car(Race race, int speed, CyclicBarrier startLine) {
        this.race = race;
        this.speed = speed;
        this.startAndFinishLines = startLine;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            this.startAndFinishLines.await();
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            if(isWinner.incrementAndGet() == 1) {
                System.out.println(name + " is Winner!!!");
            }
            startAndFinishLines.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

