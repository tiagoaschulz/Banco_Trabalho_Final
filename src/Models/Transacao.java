package Models;

import java.util.Date;

public class Transacao {
    private int id;
    private int contaId;      // ID da conta associada
    private double valor;
    private String tipo;      // "Crédito" ou "Débito"
    private Date data;

    // chamando construtor
    public Transacao() {}

    // construtor
    public Transacao(int id, int contaId, double valor, String tipo, Date data) {
        this.id = id;
        this.contaId = contaId;
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
    }

    // get e set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContaId() {
        return contaId;
    }

    public void setContaId(int contaId) {
        this.contaId = contaId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
