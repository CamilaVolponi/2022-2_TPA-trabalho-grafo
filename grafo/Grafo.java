package grafo;

import java.util.ArrayList;

public class Grafo<T>{
    private ArrayList<Vertice<T>> vertices;
    private ArrayList<Aresta<T>> arestas;
    // T = String
    //private ArrayList<Aresta<Vertice<String>>>

    public Grafo(){
        this.vertices =  new ArrayList<Vertice<T>>();
        this.arestas =  new ArrayList<Aresta<T>>();
    }

    public void adicionarVertice(Vertice<T> vertice){
        this.vertices.add(vertice);
    }

    public void adicionarAresta(String peso, T vInicio, T vFim){
        Vertice<T> inicio = this.getVertice(vInicio);
        Vertice<T> fim = this.getVertice(vFim);
        Aresta<T> aresta = new Aresta<T>(peso, inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }

    public Vertice<T> getVertice(T dado){
        Vertice<T> vertice = null;
        for(int i=0; i < this.vertices.size(); i++){
            if (this.vertices.get(i).getPosicao().equals(dado)){
                vertice = this.vertices.get(i);
                break;
            }
        }
        return vertice;
    }
}
