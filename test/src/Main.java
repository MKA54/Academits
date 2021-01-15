public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> System.out.println("Исполнение продолжено"));

        Thread thread2 = new Thread(new Numbers());

        thread2.start();

        thread1.join();
    }
}