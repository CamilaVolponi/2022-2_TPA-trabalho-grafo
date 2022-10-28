package grafo;

import java.util.ArrayList;

public class Vertice<T> {
    private int posicao;
    private T cidade;
    private ArrayList<Aresta<T>> arestasEntrada;
    private ArrayList<Aresta<T>>  arestasSaida;

    public Vertice(int posicao, T cidade){
        this.posicao = posicao;
        this.cidade = cidade;
        this.arestasEntrada =  new ArrayList<Aresta<T>>();
        this.arestasSaida =  new ArrayList<Aresta<T>>();
    }

    public int getPosicao(){
        return posicao;
    }

    public void setPosicao(int posicao){
        this.posicao = posicao;
    }

    public T getCidade(){
        return cidade;
    }

    public void setCidade(T cidade){
        this.cidade = cidade;
    }

    public void adicionarArestaEntrada(Aresta<T> aresta){
        this.arestasEntrada.add(aresta);
    }
    public void adicionarArestaSaida(Aresta<T> aresta){
        this.arestasSaida.add(aresta);
    }
}
