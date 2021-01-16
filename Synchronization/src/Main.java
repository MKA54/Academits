import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    Thread.sleep(10);

                    System.out.print(i + " ");
                }

                System.out.println();

            } catch (InterruptedException ignored) {
            }
        });

        thread1.start();

        System.out.println("Ждем другой поток");

        try {
            thread1.join();
        } catch (InterruptedException ignored) {
        }

        System.out.println("Исполнение продолжено");

        List<Integer> list = new ArrayList<>();

        Thread thread2 = new Thread(new AddNumbersToList(list));
        thread2.start();

        Thread thread3 = new Thread(new AddNumbersToList(list));
        thread3.start();

        try {
            thread2.join();
            thread3.join();
        } catch (InterruptedException ignored) {
        }

        System.out.println("Размер списка: " + list.size());
        System.out.println(list);

        ProducerConsumerManager producerConsumerManager = new ProducerConsumerManager(5, 4);
        producerConsumerManager.start();
    }

    public static class AddNumbersToList implements Runnable {
        private final List<Integer> list;

        public AddNumbersToList(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                synchronized (list) {
                    list.add(i);
                }
            }
        }
    }
}