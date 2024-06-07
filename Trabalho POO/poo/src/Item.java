import java.io.*;
import java.util.ArrayList;

public class Item {
    private int codigo;
    private String descricao;
    private double valor;

    public Item(int codigo, String descricao, double valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Item() {
        this.codigo = 0;
        this.descricao = "default_descricao";
        this.valor = 0.0;
    }

    public Item(int codigo, String descricao, String detalhes, double valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public boolean cadastrar(Item item) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("item.txt", true))) {
            writer.println(item.getCodigo() + "," + item.getDescricao() + "," + item.getValor());
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo: " + e.getMessage());
            return false;
        }
    }

    @Override
public String toString() {
    return "Item{" +
            "codigo=" + codigo +
            ", descricao='" + descricao + '\'' +
            ", valor=" + valor +
            '}';
}

    public boolean editar(Item item) {
        try {
            File inputFile = new File("item.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == item.getCodigo()) {
                    writer.write(item.getCodigo() + "," + item.getDescricao() + "," + item.getValor());
                    writer.newLine();
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.err.println("Erro: Não foi possível editar Item.");
                return false;
            }

            return true; 
        } catch (IOException e) {
            System.err.println("Erro ao editar Item: " + e.getMessage());
            return false;
        }
    }

    public Item consultar(int codigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("item.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == codigo) {
                    return new Item(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler do arquivo: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Item> listar() {
        ArrayList<Item> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("item.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                items.add(new Item(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2])));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler do arquivo: " + e.getMessage());
        }
        return items;
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