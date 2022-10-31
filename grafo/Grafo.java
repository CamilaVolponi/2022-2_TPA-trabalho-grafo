package grafo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import util.Leitor;

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

    public void obterCidadesVisinhas(){
        System.out.println("Qual o codigo da cidade que deseja? ");
        String codCidade = Leitor.getLeitor().next();
        
        for(Vertice<T> vertice: vertices){
            if(codCidade.equals(vertice.getPosicao())){
                System.out.println("Cidade escolhida:" + (String) vertice.getCidade());
                Hashtable<Vertice<T>, String> caminhos = new Hashtable<Vertice<T>, String>();
                for(Aresta<T> aresta: vertice.getArestasEntrada()){
                    /*
                    Quer dizer que a aresta que é adjacente ao vértice atual
                    está no início da aresta
                    */
                    if(!caminhos.containsKey(aresta.getInicio())){
                        caminhos.put(aresta.getInicio(), aresta.getPeso());
                    }
                }
                for(Aresta<T> aresta: vertice.getArestasSaida()){
                    /*
                    Quer dizer que a aresta que é adjacente ao vértice atual
                    está no fim da aresta
                    */
                    if(!caminhos.containsKey(aresta.getFim())){
                        caminhos.put(aresta.getInicio(), aresta.getPeso());
                    }
                }

                caminhos.forEach(
                    (Vertice<T> v, String p) -> System.out.println("Codigo: " + v.getPosicao() + "; Cidade: " + (String) v.getCidade() + "; Peso: " + p));
                }
        }
    } 

    public void obterCaminhos(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Qual o codigo da cidade que deseja? ");
        String codCidade = scanner.next();
        
        for(Vertice<T> vertice: vertices){
            if(codCidade.equals(vertice.getPosicao())){

            }
        }

        scanner.close();
    }
}
