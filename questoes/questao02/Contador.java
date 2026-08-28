public class Contador implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Contador contador = new Contador();
        Thread thread = new Thread(contador);
        thread.start();
    }
}
