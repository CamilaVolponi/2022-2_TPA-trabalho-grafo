package grafo;

public class Aresta<T> {
    private Float peso;
    private Vertice<T> destino;

    public Aresta(Float peso, Vertice<T> destino){
        this.peso = peso;
        this.destino = destino;
    }

    public Float getPeso(){
        return peso;
    }

    public void setPeso(Float peso){
        this.peso = peso;
    }

    public Vertice<T> getDestino(){
        return destino;
    }

    public void setDestino(Vertice<T> destino){
        this.destino = destino;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return destino.getValor() + "; peso: " + this.peso;
    }
}
