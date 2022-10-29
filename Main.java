import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Scanner;

import grafo.Grafo;
import grafo.Vertice;

public class Main {
    private static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) throws IOException{        
        Grafo<String> grafo = new Grafo<String>();
        try {
            lerGrafo("entrada.txt", grafo);
            
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
            System.out.println("Erro ao abrir o arquivo");
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
        System.out.println("Escolha uma opçao: ");
        return entrada.nextInt();
    }

    public static void lerGrafo(String path, Grafo<String> grafo) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String linha = "";
	
		int qtdCidades = Integer.parseInt(buffRead.readLine());

		for(int i = 0; i < qtdCidades; i++){
			linha = buffRead.readLine();
            String[] line = linha.split(";");

			String posicao = line[0];
            String cidade = line[1];
            Vertice<String> vertice = new Vertice<String>(posicao,cidade);
			grafo.adicionarVertice(vertice);
		}

		for(int i = 1; i < qtdCidades+1; i++){
            linha = buffRead.readLine();
			if(linha != null){
                for(int j = 1; j < qtdCidades+1; j++){
                    String[] line = linha.split(";");

                    for(int k = 0; k < qtdCidades; k++){
                        String peso = line[k];
                    
                        if(peso != "0,00"){
                            grafo.adicionarAresta(peso, i + "", j + "");
                        }
                    }
                }
            }
		}
		buffRead.close();
	}
}
