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

    @Override
    protected Vertice<T> clone() {
        Vertice<T> v = new Vertice<T>(this.valor);
        for(Aresta<T> a : this.destinos){
            v.destinos.add(a.clone());
        }
        return v;
    }

    @Override
    public String toString() {
        return this.valor.toString();
    }
}
