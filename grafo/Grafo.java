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
            // Verifica se o vertice atual contém o Objeto igual ao que está sendo procurado
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

    public void obterCidadesVizinhas(T dado){        
        for(Vertice<T> vertice: vertices){
            // Verifica se o vertice atual contém a Cidade igual a que está sendo procurada
            if(vertice.getValor().equals(dado)){
                System.out.println("Cidade escolhida:" + vertice.getValor());
                // Imprime todas as cidades vizinhas a esse cidade
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
                if(!marcados.contains(proximo) && !fila.contains(proximo)){
                    fila.add(proximo);
                }
            }
        }
    }
    
    public void calcularCaminhoMinimo(T origem, T destino){
        // imprimir na tela o caminho minimo da origem para o destino e a distancia total entre os dois
        // Obter todos os vertices para ligar a distancia e o predececor a um vertice
        ArrayList<No<Vertice<T>>> nos = new ArrayList<No<Vertice<T>>>();
        // Povoa a lista de nós com todos os Vertices do grafo de cidades
        No<Vertice<T>> noOrigem = null, noDestino = null;
        for(Vertice<T> vertice: vertices){
            if(vertice.getValor().equals(origem)){
                // Marcar quem é o primeiro
                noOrigem = new No<Vertice<T>>(vertice, No.PRED_ORIGEM);
                nos.add(noOrigem);
            } else if(vertice.getValor().equals(destino)){
                // Marcar quem é o primeiro
                noDestino = new No<Vertice<T>>(vertice);
                nos.add(noDestino);
            } else {
                nos.add(new No<Vertice<T>>(vertice));
            }
        }
        ArrayList<No<Vertice<T>>> rotulados = new ArrayList<No<Vertice<T>>>();
        No<Vertice<T>> noAtual = noOrigem;
        while(this.vertices.size() < rotulados.size() || !rotulados.contains(noDestino)){
            // Adiciona o no atual a lista de rotulados
            rotulados.add(noAtual);
            // Pega distancia do no atual
            float distanciaNoAtual = noAtual.getDistancia();
            // Pega aresta que liga aos nós de destino
            for(Aresta<T> aresta : noAtual.getValor().getDestinos()){
                // Percorre cada vertice vizinho (Destino, nós adjacentes)
                Vertice<T> vert = aresta.getDestino();
                int indexDoNoDestino = -1;
                // Obtem o index do nó de destino
                for(int index = 0; index < nos.size(); index++){
                    No<Vertice<T>> no = nos.get(index);
                    if(no.getValor().equals(vert)){
                        indexDoNoDestino = index;
                        break;
                    }
                }
                // Obtem o index do nó atual
                int indexDoNoAtual = nos.indexOf(noAtual);
                // Obtem o nó de destino do nó atual
                No<Vertice<T>> noDestinoDoNoAtual = nos.get(indexDoNoDestino);
                // Obtem a possível nova distância para o nó de destino
                float novaDistancia = distanciaNoAtual + aresta.getPeso();
                // verifica se a distância atual para o nó de destino é maior que a nova distância
                // Se for troca distância e o predecessor
                if(noDestinoDoNoAtual.getDistancia() > novaDistancia){
                    noDestinoDoNoAtual.setDistancia(novaDistancia);
                    noDestinoDoNoAtual.setPredecessor(indexDoNoAtual);
                }
            }
            No<Vertice<T>> noDeMenorDistancia = null;
            // Encontra o nó de menor distância que não foi rotulado
            for(No<Vertice<T>> no: nos){
                if(!rotulados.contains(no)){
                    if(noDeMenorDistancia == null){
                        noDeMenorDistancia = no;
                    } else {
                        if(noDeMenorDistancia.getDistancia() > no.getDistancia()){
                            noDeMenorDistancia = no;
                        }
                    }
                }
            }
            noAtual = noDeMenorDistancia;
        }
        imprimePredecessor(nos, noDestino, 0, true);
    }

    private float imprimePredecessor(ArrayList<No<Vertice<T>>> nos, No<Vertice<T>> no, float distanciaTotal, Boolean primeiraChamada){
        float distanciaTotalGeral = distanciaTotal;
        if(no.getPredecessor() != No.PRED_ORIGEM){
            distanciaTotalGeral = imprimePredecessor(nos, nos.get(no.getPredecessor()), distanciaTotal + no.getDistancia(), false);
        }
        Vertice<T> vert = no.getValor();
        System.out.println(vert.getValor());
        if(primeiraChamada){
            System.out.println("Distancia total: " + distanciaTotalGeral);
        }
        return distanciaTotalGeral;
    }

}