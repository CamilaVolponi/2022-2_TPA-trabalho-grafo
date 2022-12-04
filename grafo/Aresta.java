package grafo;

public class Aresta<T> {
    private float peso;
    private Vertice<T> destino;

    public Aresta(float peso, Vertice<T> destino){
        this.peso = peso;
        this.destino = destino;
    }

    public float getPeso(){
        return peso;
    }

    public void setPeso(float peso){
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

    @Override
    public Aresta<T> clone(){
        return new Aresta<T>(this.peso, this.destino);
    }
}
