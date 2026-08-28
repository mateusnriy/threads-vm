public class MinhaThread extends Thread {

    @Override
    public void run() {
        System.out.println("Thread em execução!");
    }

    public static void main(String[] args) {
        MinhaThread thread = new MinhaThread();
        thread.start();
    }
}
