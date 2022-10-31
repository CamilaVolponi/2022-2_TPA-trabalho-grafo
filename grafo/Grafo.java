package grafo;

import java.util.ArrayList;
// import java.util.Hashtable;

public class Grafo<T>{
    private ArrayList<Vertice<T>> vertices;
    // T = String
    //private ArrayList<Aresta<Vertice<String>>>

    public Grafo(){
        this.vertices =  new ArrayList<Vertice<T>>();
    }

    public void adicionarVertice(Vertice<T> vertice){
        this.vertices.add(vertice);
    }

    public Vertice<T> getVertice(T dado){
        for(Vertice<T> vert: this.vertices){
            if(vert.getValor().equals(dado)){
                return vert;
            }
        }
        return null;
    }

    public void adicionarAresta(Float peso, T vInicio, T vFim){
        Vertice<T> origem = this.getVertice(vInicio);
        Vertice<T> destino = this.getVertice(vFim);
        origem.adicionarDestino(new Aresta<T>(peso, destino));
    }

    public void obterCidadesVisinhas(T dado){        
        for(Vertice<T> vertice: vertices){
            if(vertice.getValor().equals(dado)){
                System.out.println("Cidade escolhida:" + vertice.getValor());
                for(Aresta<T> aresta: vertice.getDestinos()){
                    System.out.println(aresta);
                }
            }
        }
    }    

    public void obterCaminhos(T dado){
        ArrayList<Vertice<T>> marcados = new ArrayList<Vertice<T>>();
        ArrayList<Vertice<T>> fila = new ArrayList<Vertice<T>>();

        Vertice<T> atual = getVertice(dado);
        fila.add(atual);
        //Pego o primeiro vértice como ponto de partida e coloco na fila
        //Poderia escolher qualquer outro...
        //Mas note que dependendo do grafo pode ser que você não caminhe por todos os vétices

        //Enquanto houver vertice na fila...
        while (fila.size()>0){
            //Pego o próximo da fila, marco como visitado e o imprimo
            atual = fila.get(0);
            fila.remove(0);
            marcados.add(atual);
            System.out.println(atual.getValor());
            //Depois pego a lista de adjacencia do nó e se o nó adjacente ainda
            //não tiver sido visitado, o coloco na fila
            
            ArrayList<Aresta<T>> destinos = atual.getDestinos();
            Vertice<T> proximo;

            for (int i=0; i<destinos.size();i++){
                proximo = destinos.get(i).getDestino();
                if(!marcados.contains(proximo)){
                    fila.add(proximo);
                }
            }
        }
    }
}
