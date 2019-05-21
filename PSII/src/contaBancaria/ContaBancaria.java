
package contaBancaria;

public class ContaBancaria {
    private long id;
    private String nomeTitular;
    private double saldo;
    private int nAgencia;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getnAgencia() {
        return nAgencia;
    }

    public void setnAgencia(int nAgencia) {
        this.nAgencia = nAgencia;
    }
}
