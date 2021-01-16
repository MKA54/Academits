import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerManager {
    private static final int CAPACITY = 10;

    private final int producersCount;
    private final int consumersCount;

    private final List<String> list = new ArrayList<>();
    private final Object lock = new Object();

    public ProducerConsumerManager(int producersCount, int consumersCount) {
        if (producersCount <= 0) {
            throw new IllegalArgumentException("Count of producers: " + producersCount);
        }

        if (consumersCount <= 0) {
            throw new IllegalArgumentException("Count of consumers: " + consumersCount);
        }

        this.producersCount = producersCount;
        this.consumersCount = consumersCount;
    }

    public void start() {
        for (int i = 1; i <= producersCount; i++) {
            Thread thread = new Thread(new ProducerRunnable(this));
            thread.start();
        }

        for (int i = 1; i <= consumersCount; i++) {
            Thread thread = new Thread(new ConsumerRunnable(this));
            thread.start();
        }
    }

    public void addItem(String item) throws InterruptedException {
        synchronized (lock) {
            while (list.size() >= CAPACITY) {
                lock.wait();
            }

            list.add(item);
            lock.notifyAll();
        }

        System.out.println("Добавлен элемент: " + item + ". Размер списка: " + list.size());
    }

    public String getItem() throws InterruptedException {
        synchronized (lock) {
            while (list.isEmpty()) {
                lock.wait();
            }

            String item = list.remove(list.size() - 1);

            lock.notifyAll();

            return item;
        }
    }
}