package aplicativo;

public class Aplicativo {

    private long id;
    private String nome;
    private String desenvolvedor;
    private int nDownloads;

    Aplicativo(){}
    Aplicativo(String nome, String desenvolvedor, int nDownloads) {
        this.nome = nome;
        this.desenvolvedor = desenvolvedor;
        this.nDownloads = nDownloads;

    }
    
    public long getId(){
    return id;
    }
    
    public void setId(long id){
        this.id=id;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesenvolvedor() {
        return desenvolvedor;
    }

    public void setDesenvolvedor(String desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }

    public int getnDownloads() {
        return nDownloads;
    }

    public void setnDownloads(int nDownloads) {
        this.nDownloads = nDownloads;
    }

    @Override
    public String toString() {
        return "Aplicativos: " + this.nome + " - " + this.desenvolvedor + " - " + this.nDownloads + " - " + "(" + this.id + ")";

    }
}
