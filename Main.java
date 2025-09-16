import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Criar e iniciar 21 threads
        for (int i = 1; i <= 21; i++) {
            new Thread(new TransacaoBDThread(i)).start();
        }
    }
}

class TransacaoBDThread implements Runnable {

    private int id;
    private Random rand;

    public TransacaoBDThread(int id) {
        this.id = id;
        this.rand = new Random();
    }

    @Override
    public void run() {
        // Comportamento baseado no ID da thread
        if (id % 3 == 1) {
            // Comportamento para threads com id % 3 == 1
            executarComportamentoTipo1();
        } else if (id % 3 == 2) {
            // Comportamento para threads com id % 3 == 2
            executarComportamentoTipo2();
        } else {
            // Comportamento para threads com id % 3 == 0
            executarComportamentoTipo3();
        }
    }

    private void executarComportamentoTipo1() {
        try {
            log("Thread " + id + " começando cálculos.");
            sleepRandom(200, 1000);
            log("Thread " + id + " realizando transação de BD.");
            sleepRandom(1000, 1000);
            log("Thread " + id + " terminando cálculos.");
            sleepRandom(200, 1000);
            log("Thread " + id + " realizando transação de BD.");
            sleepRandom(1000, 1000);
            log("Thread " + id + " finalizou todas as operações.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void executarComportamentoTipo2() {
        try {
            log("Thread " + id + " começando cálculos.");
            sleepRandom(500, 1500);
            log("Thread " + id + " realizando transação de BD.");
            sleepRandom(1500, 1500);
            log("Thread " + id + " terminando cálculos.");
            sleepRandom(500, 1500);
            log("Thread " + id + " realizando transação de BD.");
            sleepRandom(1500, 1500);
            log("Thread " + id + " terminando cálculos.");
            sleepRandom(500, 1500);
            log("Thread " + id + " realizando transação de BD.");
            log("Thread " + id + " finalizou todas as operações.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void executarComportamentoTipo3() {
        try {
            log("Thread " + id + " começando cálculos.");
            sleepRandom(1000, 2000);
            log("Thread " + id + " realizando transação de BD.");
            sleepRandom(1500, 1500);
            log("Thread " + id + " terminando cálculos.");
            sleepRandom(1000, 2000);
            log("Thread " + id + " realizando transação de BD.");
            sleepRandom(1500, 1500);
            log("Thread " + id + " terminando cálculos.");
            sleepRandom(1000, 2000);
            log("Thread " + id + " realizando transação de BD.");
            log("Thread " + id + " finalizou todas as operações.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para simular um tempo de execução aleatório entre min e max (em milissegundos)
    private void sleepRandom(int min, int max) throws InterruptedException {
        int time = rand.nextInt((max - min) + 1) + min;
        Thread.sleep(time);
    }

    // Método para exibir mensagens no console com o ID da thread
    private void log(String mensagem) {
        System.out.println(mensagem);
    }
}
