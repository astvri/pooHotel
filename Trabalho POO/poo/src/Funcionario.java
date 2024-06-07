import java.io.*;
import java.util.ArrayList;

public class Funcionario extends Pessoa {
    private String setor;

    public Funcionario(String cpf, String nome, String email, String setor) {
        super(cpf, nome, email);
        this.setor = setor;
    }

    public Funcionario() {
        super("default_cpf", "default_nome", "default_email");
        this.setor = "default_setor";
    }

    public boolean cadastrar(Funcionario funcionario) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("funcionario.txt", true))) {
            writer.println(funcionario.getCpf() + "," + funcionario.getNome() + "," + funcionario.getEmail() + "," + funcionario.getSetor());
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "cpf='" + getCpf() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", setor='" + setor + '\'' +
                '}';
    }

    public boolean editar(Funcionario funcionario) {
        try {
            File inputFile = new File("funcionario.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(funcionario.getCpf())) {
                    writer.write(funcionario.getCpf() + "," + funcionario.getNome() + "," + funcionario.getEmail() + "," + funcionario.getSetor());
                    writer.newLine();
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.err.println("Erro: Não foi possivel editar Funcionario.");
                return false;
            }

            return true; 
        } catch (IOException e) {
            System.err.println("Erro ao editar Funcionario: " + e.getMessage());
            return false;
        }
    }

    public Funcionario consultar(String cpf) {
        try (BufferedReader reader = new BufferedReader(new FileReader("funcionario.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(cpf)) {
                    return new Funcionario(parts[0], parts[1], parts[2], parts[3]);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Funcionario> listar() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("funcionario.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                funcionarios.add(new Funcionario(parts[0], parts[1], parts[2], parts[3]));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return funcionarios;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
}
