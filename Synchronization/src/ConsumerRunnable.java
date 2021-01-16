public class ConsumerRunnable implements Runnable {
    private final ProducerConsumerManager producerConsumerManager;

    public ConsumerRunnable(ProducerConsumerManager producerConsumerManager) {
        this.producerConsumerManager = producerConsumerManager;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(1500);

                String item = producerConsumerManager.getItem();

                System.out.println("Обработан " + item);
            }
        } catch (InterruptedException ignored) {
        }
    }
}