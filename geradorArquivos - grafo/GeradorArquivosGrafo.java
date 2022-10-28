/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradorarquivos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author victoriocarvalho
 */
public class GeradorArquivosGrafo {
    
    final char vogais[] = {'a', 'e', 'i', 'o','u','A','E','I','O','U'};
    final Random rand = new Random();
    
    private boolean ehVogal (char c){
        for (char l: vogais){
            if (l==c) return true;
        }
        return false;
    }
    
    private char geraVogal(boolean min){
        if (min)
            return vogais[rand.nextInt(5)];
        else
            return vogais[5+rand.nextInt(5)];       
    }

    private char geraLetra(boolean min){
        if (min)
            return (char) ('a'+rand.nextInt(26));
        else
            return (char) ('A'+rand.nextInt(26));
    }

    
    private String geraPalavra(int tam){
        int cont;
        String palavra = "";

        palavra+= geraLetra(false);
        for(cont=1;cont<tam;cont++){
            if (ehVogal(palavra.charAt(cont-1)))
                palavra+=geraLetra(true);
            else
                palavra+=geraVogal(true);        
        }
        return palavra;
    }    
    
     private void geraArquivo(int n){
        int i;
        String nome;
        FileWriter arq;
        try {
            arq = new FileWriter("entrada.txt");
            PrintWriter gravarArq = new PrintWriter(arq);
            //Gerando linha com quantidade de cidades
            gravarArq.println(n);
            //Gerando linhas com códigos e nomes de cidades
            for(i=1;i<=n;i++){
                nome = geraPalavra(3+rand.nextInt(10));
                gravarArq.printf("%d;%s%n",i, nome);
            }
            //Vou gerar as distancias.
            //Como o grafo nao será direcional e nao tenho arestas ligando um vertice a ele mesmo
            // teria uma matriz de adjacencia na qual a diagonal principal é toda de zeros
            // e os elementos acima da diagonal são iguais aos abaixo, isto é, o elemento A(l,c) = A(c,l)
            //Assim vou gerar apenas os elementos que ficam acima da diagonal principal e armazenar num vetor
            //Esse vetor terá seu tamanho dados por (qtd*qtd-qtd)/2 (resultado de uma somatoria onde a primeira lina tem qtd-1 elementos
            //e vai diminuindo de um em um até que a última tem zero.
            int tamVetDist = (n*n-n)/2;
            double distancias[] = new double[tamVetDist];
            int l,c;
            i=0;
            for(l=0;l<n;l++){
                for(c=l+1;c<n;c++){
                    distancias[i]=geraDistancia(l,c);
                    i++;
                }
            }

            //Nos laços abaixo imprimo a matriz de adjacencias completa, inclusive diagonal principal e elementos abaixo dela
            //Para achar no vetor a distancia entre elementos usei a formula que desenvolvi observado que a primeira linha tem qtd-1 elementos no vetor,
            //a segunda linha qtd-2 e assim sucessivamente até que a última linha tem zero.
            // Assim, vi que para obter o indice do primeiro elemento da linha no vetor bastava resolver uma soma de PA e que a partir dele
            //bastava somar a coluna menos linha -1. 
            for(l=0;l<n;l++){
                for(c=0;c<n-1;c++){
                    if(l==c)
                        gravarArq.printf("%.2f;",0.0);
                    else if (l<c)
                        gravarArq.printf("%.2f;",distancias[l*n-(l*l+l)/2+c-l-1]);
                    else
                        gravarArq.printf("%.2f;",distancias[c*n-(c*c+c)/2+l-c-1]);
                }
                if(l==(n-1))
                    gravarArq.printf("%.2f%n",0.0);
                else
                    gravarArq.printf("%.2f%n",distancias[l*n-(l*l+l)/2+c-l-1]);
            }
            arq.close();
        } catch (IOException ex) {
            Logger.getLogger(GeradorArquivosGrafo.class.getName()).log(Level.SEVERE, null, ex);
        }
     }        

    //Esse método sempre será chamado com coluna > linha pois a ideia é só gerar os elementos acima da diagonal principal
    double geraDistancia(int linha, int coluna){
        double r=rand.nextDouble();
        //Para garantir que o grafo será conexo garantirei que sempre haverá arestas entre vertices que o codigo diferem de 1
        //A distancia dessas arestas será um numero aleatório entre 10 e 110
        if (coluna-linha==1){
            return r*100+10;
        }
        //Para os demais gero um aleatório entre 0 e 1. Se for menor que 0.3 não haverá aresta entre eles
        //caso contrário haverá aresta e a distancia da aresta será dada pelo numero gerado * 100, ou seja, será algo entre 30 e 100.
        else{
            if (r<0.3)
                return 0.0;
            else
                return r * 100;
        }
    }
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        GeradorArquivosGrafo g = new GeradorArquivosGrafo();
        
        
        int TAM = 5;
        long tempoInicial = System. currentTimeMillis();        
        g.geraArquivo(TAM);
        long tempoFinal = System. currentTimeMillis();
        
        System.out.println("Tempo Total de geração do arquivo em ms: " + (tempoFinal - tempoInicial));
    }
    
}
