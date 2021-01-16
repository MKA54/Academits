public class ProducerRunnable implements Runnable {
    private final ProducerConsumerManager producerConsumerManager;

    public ProducerRunnable(ProducerConsumerManager producerConsumerManager) {
        this.producerConsumerManager = producerConsumerManager;
    }

    @Override
    public void run() {
        try {
            int i = 1;

            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(1000);

                producerConsumerManager.addItem("Элемент " + i);

                ++i;
            }
        } catch (InterruptedException ignored) {
        }
    }
}