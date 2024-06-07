import java.io.*;
import java.util.ArrayList;

public class Categoria {
    private int codigo;
    private String descricao;
    private double valor;

    public Categoria(int codigo, String descricao, double valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public boolean cadastrar(Categoria categoria) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("categoria.txt", true))) {
            writer.println(categoria.getCodigo() + "," + categoria.getDescricao() + "," + categoria.getValor());
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo" + e.getMessage());
            return false;
        }
    }

    @Override
    public String toString() {
        return "Categoria [codigo=" + codigo + ", descricao=" + descricao + ", valor=" + valor + "]";
    }

    public boolean editar(Categoria categoria) {
        try {
            File inputFile = new File("categoria.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == categoria.getCodigo()) {
                    writer.write(categoria.getCodigo() + "," + categoria.getDescricao() + "," + categoria.getValor());
                    writer.newLine();
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.err.println("Erro: Não foi possível editar Categoria.");
                return false;
            }

            return true; 
        } catch (IOException e) {
            System.err.println("Erro ao editar Categoria: " + e.getMessage());
            return false;
        }
    }

    public Categoria consultar(int codigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("categoria.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == codigo) {
                    return new Categoria(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Categoria> listar() {
        ArrayList<Categoria> categorias = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("categoria.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                categorias.add(new Categoria(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2])));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return categorias;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
