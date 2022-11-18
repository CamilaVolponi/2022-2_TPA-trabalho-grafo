package grafo;

public class No_v2<T>{
    private T valor;
    private float distancia;
    private No_v2<T> predecessor;
    static final float INFINITO = Float.POSITIVE_INFINITY;

    public No_v2(T valor, Boolean ehOrigem){
        this.valor = valor;
        if(ehOrigem){
            this.distancia = 0;
        } else {
            this.distancia = INFINITO;
        }
        this.predecessor = null;
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

    public No_v2<T> getPredecessor() {
        return predecessor;
    }
    public void setPredecessor(No_v2<T> predecessor) {
        this.predecessor = predecessor;
    }
}