import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        List<Product> product = new ArrayList<>();

        System.out.print("Enter file path: ");
        String caminhoArquivo = "D:\\manipulandoArquivo\\in.txt";

        File caminho = new File(caminhoArquivo);

        boolean criarPasta = new File(caminhoArquivo + "\\subPasta").mkdir();        
        
        String caminhoNovoArquivo = caminho.getParent() + "\\subPasta\\out.txt";
        File arquivo = new File(caminhoNovoArquivo);
        

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))){            

            String linha = br.readLine();
            while (linha != null) {
                String[] vect = linha.split(",");
                product.add(new Product(vect[0], Double.parseDouble(vect[1]), Integer.parseInt(vect[2])));                
                linha = br.readLine();
            }

            arquivo.createNewFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoNovoArquivo))) {
                for (Product string : product) {
                    bw.write(string.getName() + ", " + String.format("%.2f", string.total()));
                    bw.newLine();                    
                }
            } catch (IOException e) {
                e.printStackTrace();
            }            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}
