import java.io.*;
import java.util.ArrayList;

public class Quarto {
    private int codigo;
    private Categoria categoria;
    private String status;

    public Quarto(int codigo, Categoria categoria, String status) {
        this.codigo = codigo;
        this.categoria = categoria;
        this.status = status;
    }

    public boolean cadastrar(Quarto quarto) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("quarto.txt", true))) {
            writer.println(quarto.getCodigo() + "," + quarto.getCategoria().getCodigo() + "," + quarto.getStatus());
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo: " + e.getMessage());
            return false;
        }
    }

    @Override
public String toString() {
    return "Quarto{" +
            "codigo=" + codigo +
            ", categoria=" + categoria +
            ", status='" + status + '\'' +
            '}';
}

    public boolean editar(Quarto quarto) {
        try {
            File inputFile = new File("quarto.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == quarto.getCodigo()) {
                    writer.write(quarto.getCodigo() + "," + quarto.getCategoria().getCodigo() + "," + quarto.getStatus());
                    writer.newLine();
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.err.println("Erro: Não foi possível editar Quarto.");
                return false;
            }

            return true; 
        } catch (IOException e) {
            System.err.println("Erro ao editar Quarto: " + e.getMessage());
            return false;
        }
    }

    public Quarto consultar(int codigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("quarto.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == codigo) {
                    Categoria categoria = new Categoria(Integer.parseInt(parts[1]), "dummy", 0.0); 
                    return new Quarto(Integer.parseInt(parts[0]), categoria, parts[2]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error while reading from file: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Quarto> listar() {
        ArrayList<Quarto> quartos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("quarto.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Categoria categoria = new Categoria(Integer.parseInt(parts[1]), "dummy", 0.0); 
                quartos.add(new Quarto(Integer.parseInt(parts[0]), categoria, parts[2]));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return quartos;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}