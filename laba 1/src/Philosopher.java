import java.util.concurrent.Semaphore;

class Philosopher extends Thread {
    private final int id;
    private final Semaphore leftFork;
    private final Semaphore rightFork;

    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (true) {
                hm();
                nom();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void hm() throws InterruptedException {
        System.out.println("Number " + id + " hmhmhms.");
        Thread.sleep(500);
    }

    private void nom() throws InterruptedException {
        leftFork.acquire();
        rightFork.acquire();

        System.out.println("Number " + id + " nomnomnoms.");
        Thread.sleep(450);

        rightFork.release();
        leftFork.release();
    }
}