import java.io.*;
import java.util.ArrayList;

public class Servico {
    private int codigo;
    private String descricao;
    private double valor;

    
    public Servico(int codigo, String descricao, double valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
    }

    
    public boolean cadastrar(Servico servico) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("servico.txt", true))) {
            writer.println(servico.getCodigo() + "," + servico.getDescricao() + "," + servico.getValor());
            return true;
        } catch (IOException e) {
            System.err.println("Error ao editar arquivo: " + e.getMessage());
            return false;
        }
    }

    @Override
public String toString() {
    return "Servico{" +
            "codigo=" + codigo +
            ", descricao='" + descricao + '\'' +
            ", valor=" + valor +
            '}';
}

    
    public boolean editar(Servico servico) {
        try {
            File inputFile = new File("servico.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == servico.getCodigo()) {
                    writer.write(servico.getCodigo() + "," + servico.getDescricao() + "," + servico.getValor());
                    writer.newLine();
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.err.println("Erro: Não foi possível editar Servico");
                return false;
            }

            return true;
        } catch (IOException e) {
            System.err.println("Error ao editar Servico: " + e.getMessage());
            return false;
        }
    }

    
    public Servico consultar(int codigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("servico.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == codigo) {
                    return new Servico(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error while reading from file: " + e.getMessage());
        }
        return null;
    }

    
    public ArrayList<Servico> listar() {
        ArrayList<Servico> servicos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("servico.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                servicos.add(new Servico(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2])));
            }
        } catch (IOException e) {
            System.err.println("Error ao ler o arquivo: " + e.getMessage());
        }
        return servicos;
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
