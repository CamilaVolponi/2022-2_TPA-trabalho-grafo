package grafo;

import java.util.ArrayList;

public class Vertice<T> {
    private T valor;
    private ArrayList<Aresta<T>> destinos;

    public Vertice(T valor){
        this.valor = valor;
        this.destinos =  new ArrayList<Aresta<T>>();
    }

    public T getValor(){
        return valor;
    }

    public void setValor(T valor){
        this.valor = valor;
    }

    public void adicionarDestino(Aresta<T> aresta){
        this.destinos.add(aresta);
    }

    public ArrayList<Aresta<T>> getDestinos(){
        return destinos;
    }
}
