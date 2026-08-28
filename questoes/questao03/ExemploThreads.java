public class ExemploThreads {

    static class MinhaThread extends Thread {
        public MinhaThread(String nome) {
            super(nome);
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(getName());
            }
        }
    }

    public static void main(String[] args) {
        Thread threadA = new MinhaThread("Thread A");
        Thread threadB = new MinhaThread("Thread B");
        Thread threadC = new MinhaThread("Thread C");

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
