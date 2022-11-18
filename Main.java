import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import grafo.Cidade;
import grafo.Grafo;
import grafo.Vertice;
import util.Leitor;

public class Main {
    static Grafo<Cidade> grafo = new Grafo<Cidade>();
    public static void main(String[] args) throws IOException{        
        try {
            lerGrafo("entrada.txt", grafo);
            
            int selection, codCidade, codOrigem, codDestino;
            Cidade origem, destino;
            Grafo<Cidade> grafoNovo;
            do {
                printMenu();
                selection = getSelection();
                switch (selection) {
                    case 1:
                        System.out.println("Qual o codigo da cidade que deseja? ");
                        codCidade = Leitor.getLeitor().nextInt();
                        grafo.obterCidadesVizinhas(new Cidade(codCidade, ""));
                        break;
                    case 2:
                        System.out.println("De qual o codigo da cidade que deseja? ");
                        codCidade = Leitor.getLeitor().nextInt();
                        grafo.obterCaminhos(new Cidade(codCidade, ""));
                        break;
                    case 3:
                        System.out.println("Qual o codigo da cidade de origem? ");
                        codOrigem = Leitor.getLeitor().nextInt();
                        System.out.println("Qual o codigo da cidade de destino? ");
                        codDestino = Leitor.getLeitor().nextInt();
                        origem = verificaSeCidadeExiste(codOrigem);
                        destino = verificaSeCidadeExiste(codDestino);
                        if(origem != null && destino != null){
                            grafo.calcularCaminhoMinimo_v2(origem, destino);
                        }else{
                            System.out.println("Alguma cidade informada nao existe!");
                        }                        
                        break;
                    case 4:
                        System.out.println("Qual o codigo da cidade de origem? ");
                        codOrigem = Leitor.getLeitor().nextInt();
                        origem = verificaSeCidadeExiste(codOrigem);
                        if(origem != null){
                            grafoNovo = grafo.gerarArvoreGeradoraMinima(origem);
                            if(grafoNovo == null){
                                System.out.println("A origem não está no grafo!");
                            } else {
                                grafoNovo.imprimirArestas();
                            }
                        }else{
                            System.out.println("Alguma cidade informada não existe!");
                        }                        
                        break;
                    case 5:
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
        System.out.println("3 - Calcular caminho minimo");
        System.out.println("4 - Calcular Arvore geradora minima");
        System.out.println("5 - Sair");
        System.out.println("======================================");
    } 
    
    private static int getSelection(){
        System.out.println("Escolha uma opcao: ");
        return Leitor.getLeitor().nextInt();
    }

    public static Cidade verificaSeCidadeExiste(int codCidade){
        Vertice<Cidade> vert = grafo.getVertice(new Cidade(codCidade, ""));
        return vert != null ? vert.getValor() : null;
    }

    public static void lerGrafo(String path, Grafo<Cidade> grafo) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String linha = "";
	
		int qtdCidades = Integer.parseInt(buffRead.readLine());

		for(int i = 0; i < qtdCidades; i++){
			linha = buffRead.readLine();
            String[] line = linha.split(";");

			int cod = Integer.parseInt(line[0]);
            String cidade = line[1];
            Vertice<Cidade> vertice = new Vertice<Cidade>(new Cidade(cod,cidade));
			grafo.adicionarVertice(vertice);
		}

		for(int i = 1; i < qtdCidades + 1; i++){
            linha = buffRead.readLine();
			if(linha != null){
                String[] line = linha.split(";");

                for(int k = 0; k < qtdCidades; k++){
                    float peso = Float.parseFloat((line[k]).replaceAll(",", "."));

                    if(peso != 0){
                        Cidade origem = new Cidade(i,"");
                        Cidade destino = new Cidade((k+1),"");
                        grafo.adicionarAresta(peso, origem, destino);
                    }
                }
            }
		}
		buffRead.close();
	}
}
