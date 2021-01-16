public class ConsumerRunnable implements Runnable {
    private final ProducerConsumerManager producerConsumerManager;

    public ConsumerRunnable(ProducerConsumerManager producerConsumerManager) {
        this.producerConsumerManager = producerConsumerManager;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                String item = producerConsumerManager.getItem();

                System.out.println("Обработан " + item);
            }
        } catch (InterruptedException ignored) {
        }
    }
}