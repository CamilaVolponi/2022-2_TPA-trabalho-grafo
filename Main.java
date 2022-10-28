import java.io.IOException;

import java.util.Scanner;

import grafo.Grafo;
import grafo.Vertice;

public class Main {
    private static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) throws IOException{        
        Grafo<Vertice> tree = new Grafo<Vertice>();
        try {
            ManipulaArquivo.lerGrafo("entrada.txt");
            
            int selection;
            do {
                printMenu();
                selection = getSelection();
                switch (selection) {
                    case 1:
                        //METODO OBTER CIDADES VIZINHAS
                        break;
                    case 2:
                        //METODO OBTER CAMINHOS A PARTIR DA CIDADE
                        break;
                    case 3:
                        //METODO SAIR
                        break;
                    default:
                        break;
                }
            } while(selection != 5);
        } catch (IOException e) {
            println("Erro ao abrir o arquivo");
        }
    }

    private static void printMenu(){
        System.out.println("=================MENU=================");
        System.out.println("1 - Obter cidades vizinhas");
        System.out.println("2 - Obter todos os caminhos a partir de uma cidade");
        System.out.println("5 - Sair");
        System.out.println("======================================");
    } 
    
    private static int getSelection(){
        System.out.println("Escolha uma opção: ");
        return entrada.nextInt();
    }
}
