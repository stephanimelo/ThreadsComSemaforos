public class CozinhaSimulacao {

    public static void main(String[] args) {
        final int totalPratos = 5;
        for (int i = 1; i <= totalPratos; i++) {
            new Thread(new Prato(i)).start();
        }
    }
}

class Prato implements Runnable {

    private final int id;
    private final String nome;
    private final int tempoMin; // em milissegundos
    private final int tempoMax; // em milissegundos
    private static final Object lockEntrega = new Object();

    public Prato(int id) {
        this.id = id;
        if (id % 2 == 1) {
            nome = "Sopa de Cebola";
            tempoMin = 500;
            tempoMax = 800;
        } else {
            nome = "Lasanha a Bolonhesa";
            tempoMin = 600;
            tempoMax = 1200;
        }
    }

    @Override
    public void run() {
        try {
            System.out.printf("Prato %d (%s) iniciou o cozimento.%n", id, nome);

            // Determinar tempo total do cozimento aleatório
            int tempoCozimento = tempoMin + (int) ((tempoMax - tempoMin) * Math.random());

            int tempoPassado = 0;
            while (tempoPassado < tempoCozimento) {
                Thread.sleep(100);
                tempoPassado += 100;

                // Percentual arredondado para cima
                int percentual = Math.min(100, (int) (((double) tempoPassado / tempoCozimento) * 100));
                System.out.printf("Prato %d (%s) cozinhando: %d%%%n", id, nome, percentual);
            }

            System.out.printf("Prato %d (%s) terminou o cozimento.%n", id, nome);

            // Entrega sincronizada — apenas um prato entregue por vez
            synchronized (lockEntrega) {
                System.out.printf("Prato %d (%s) iniciando entrega.%n", id, nome);
                Thread.sleep(500); // tempo de entrega
                System.out.printf("Prato %d (%s) entregue.%n", id, nome);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
