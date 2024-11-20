package Models;

public class Endereco {
    private String cep;
    private String logradouro;
    private int numero;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return logradouro + ", " + numero + " - " + bairro + " - " + cidade + "/" + estado + " - CEP: " + cep;
    }
}

