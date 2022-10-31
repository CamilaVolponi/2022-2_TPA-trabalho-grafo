package grafo;

public class Cidade{
    private int codigo;
    private String nome;
    
    public Cidade(int cod, String nome){
        this.codigo = cod;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "codigo: " + codigo + "; nome: " + nome;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        int cod = ((Cidade) obj).codigo;
        return codigo == cod;
    }
}