import java.io.*;
import java.util.ArrayList;

public class CategoriaItem {
    private Item item;
    private Categoria categoria;
    private int quantidade;

    public CategoriaItem(Item item, Categoria categoria, int quantidade) {
        this.item = item;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }

    public boolean cadastrar(CategoriaItem categoriaItem) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("categoria_item.txt", true))) {
            writer.println(categoriaItem.getItem().getCodigo() + "," + categoriaItem.getCategoria().getCodigo() + "," + categoriaItem.getQuantidade());
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo " + e.getMessage());
            return false;
        }
    }

    @Override
public String toString() {
    return "CategoriaItem{" +
            "item=" + item +
            ", categoria=" + categoria +
            ", quantidade=" + quantidade +
            '}';
}

    public boolean editar(CategoriaItem categoriaItem) {
        try {
            File inputFile = new File("categoria_item.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == categoriaItem.getItem().getCodigo() && Integer.parseInt(parts[1]) == categoriaItem.getCategoria().getCodigo()) {
                    writer.write(categoriaItem.getItem().getCodigo() + "," + categoriaItem.getCategoria().getCodigo() + "," + categoriaItem.getQuantidade());
                    writer.newLine();
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.err.println("Erro: Não foi possível editar CategoriaItem.");
                return false;
            }

            return true; 
        } catch (IOException e) {
            System.err.println("Error ao editar CategoriaItem: " + e.getMessage());
            return false;
        }
    }

    public CategoriaItem consultar(Item item, Categoria categoria) {
        try (BufferedReader reader = new BufferedReader(new FileReader("categoria_item.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == item.getCodigo() && Integer.parseInt(parts[1]) == categoria.getCodigo()) {
                    return new CategoriaItem(item, categoria, Integer.parseInt(parts[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<CategoriaItem> listar() {
        ArrayList<CategoriaItem> categoriaItems = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("categoria_item.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Item item = new Item(Integer.parseInt(parts[0]), "dummy", 0.0); // Replace with actual item lookup
                Categoria categoria = new Categoria(Integer.parseInt(parts[1]), "dummy", 0.0); // Replace with actual category lookup
                categoriaItems.add(new CategoriaItem(item, categoria, Integer.parseInt(parts[2])));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return categoriaItems;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
