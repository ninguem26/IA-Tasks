/**
 * Created by julio on 28/07/17.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputFile {

    public static void main(String[] args) {
        List<Symbol> simbolos1 = new ArrayList<Symbol>();
        List<Symbol> simbolos2 = new ArrayList<Symbol>();

        Scanner ler = new Scanner(System.in);

        System.out.printf("Informe o nome de arquivo texto:\n");
        String nome = ler.nextLine();

        System.out.printf("\nConteúdo do arquivo texto:\n");
        try {
            FileReader arq = new FileReader(nome);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine(); // lê a primeira linha
// a variável "linha" recebe o valor "null" quando o processo
// de repetição atingir o final do arquivo texto
            while (linha != null) {
                Symbol s = new Symbol();
                if (linha.length() == 1){
                    s.s1 = linha;
                    s.s2 = new String();
                    s.operacao = "2";

                    simbolos1.add(s);
                }else{
                    int i = 0;
                    String aux[] = new String[3];
                    String word = new String();
                    for (char a : linha.toCharArray()) {
                        if(a != ' '){
                            word = word + "" + a + "";
                        }else{
                            aux[i] = word;
                            word = new String();
                            i++;
                        }
                    }
                    s.s1 = aux[0];
                    s.s2 = word;
                    s.operacao = "1";

                    simbolos2.add(s);
                }

                linha = lerArq.readLine(); // lê da segunda até a última linha
            }

            Symbol ultimoSimbolo = simbolos1.get(simbolos1.size() - 1);
            simbolos1.remove(ultimoSimbolo);

            boolean end = false;

            while(!end) {
                List<Symbol> simbolosAux = new ArrayList<Symbol>();
                for (Symbol symbol : simbolos1) {
                    symbol.resolvido = true;
                    for (Symbol s : simbolos2) {
                        if (symbol.s1.equals(s.s1)) {
                            Symbol simbolo = new Symbol();
                            simbolo.s1 = s.s2;
                            simbolo.s2 = new String();
                            simbolo.operacao = "2";
                            simbolo.resolvido = true;
                            if (!simbolos1.contains(simbolo)) {
                                simbolo.resolvido = false;
                                if (!simbolos1.contains(simbolo)) {
                                    simbolosAux.add(simbolo);
                                }
                            }
                        }
                    }
                }

                // /home/julio/Documentos/teste
                if(simbolosAux.size() <= 0){
                    System.out.println("Resultado não obtido");
                    end = true;
                }else{
                    for (Symbol s : simbolosAux) {
                        if(s.s1.equals(ultimoSimbolo.s1)){
                            System.out.println("Resultado obtido");
                            end = true;

                            break;
                        }
                    }

                    if(!end){
                        simbolos1 = simbolosAux;
                    }
                }
            }

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

        System.out.println();
    }
}