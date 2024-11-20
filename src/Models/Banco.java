package Models;

public class Banco {
        private String nome;

        public Banco(String nome) {
            this.nome = nome;
        }

        public static void main(String[] args) {
            Banco banco = new Banco("Banco");
            banco.iniciarSistema();
        }

        // Método para iniciar o sistema
        public void iniciarSistema() {
            System.out.println("Bem-vindo ao " + nome + "! O sistema está sendo iniciado...");
        }
}