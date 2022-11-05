package grafo;

public class No<T>{
    private T valor;
    private float distancia;
    private int predecessor;
    static final float INFINITO = 999999999;
    static final int PRED_ORIGEM = -1;

    public No(T valor){
        this.valor = valor;
        this.distancia = INFINITO;
        this.predecessor = -2;
    }

    public No(T valor, int predecessor){
        this.valor = valor;
        this.distancia = 0;
        this.predecessor = predecessor;
    }

    public T getValor() {
        return valor;
    }

    public Float getDistancia() {
        return distancia;
    }
    public void setDistancia(Float distancia) {
        this.distancia = distancia;
    }

    public int getPredecessor() {
        return predecessor;
    }
    public void setPredecessor(int predecessor) {
        this.predecessor = predecessor;
    }
}