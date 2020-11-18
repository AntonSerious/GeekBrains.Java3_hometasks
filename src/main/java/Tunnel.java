import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    Semaphore tunnelEntry = new Semaphore(2); //По туннелю может ехать только 2 гонщика

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                tunnelEntry.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                tunnelEntry.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}