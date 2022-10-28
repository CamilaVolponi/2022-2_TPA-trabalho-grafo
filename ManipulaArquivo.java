import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import grafo.Grafo;

public class ManipulaArquivo {
    public static void lerGrafo(String path) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String linha = "";
	
		int qtdCidades = Integer.parseInt(buffRead.readLine());

		
		for(int i = 0; i < qtdCidades; i++){
			linha = buffRead.readLine();
            String[] line = linha.split(";");

			int posicao = Integer.parseInt(line[0]);
            String cidade = line[1];

			Grafo.adicionarVertice(posicao,cidade);
		}

		for(int i = 1; i < qtdCidades+1; i++){
			// for(int j = 1; j < qtdCidades+1; j++){
			linha = buffRead.readLine();
			String[] line = linha.split(";");

			int peso1 = Integer.parseInt(line[0]);
            int peso2 = Integer.parseInt(line[1]);
			int peso3 = Integer.parseInt(line[2]);
				
			if(peso1 != 0){
				Grafo.adicionarAresta(peso1, i, 1);
			}
			if(peso2 != 0){
				Grafo.adicionarAresta(peso2, i, 2);
			}
			if(peso3 != 0){
				Grafo.adicionarAresta(peso3, i, 3);
			}
		}

		buffRead.close();
	}

}
