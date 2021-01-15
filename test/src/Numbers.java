public class Numbers implements Runnable {
    @Override
    public void run() {
        int i = 1;

        while (i <= 10) {
            System.out.println(i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }

            ++i;
        }
    }
}