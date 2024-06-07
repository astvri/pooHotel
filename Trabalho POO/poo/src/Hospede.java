import java.util.ArrayList;
import java.io.*;

public class Hospede extends Pessoa {
    private String enderecoCompleto;

        public Hospede(String cpf, String nome, String email, String enderecoCompleto) {
            super(cpf, nome, email);
            this.enderecoCompleto = enderecoCompleto;
        }

        public Hospede() {
            super("default_cpf", "default_nome", "default_email");
        }

    
    public boolean cadastrar(Hospede hospede) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("hospede.txt", true))) {
            writer.println(hospede.getCpf() + "," + hospede.getNome() + "," + hospede.getEmail() + "," + hospede.getEnderecoCompleto());
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String toString() {
        return "Hospede{" +
                "cpf='" + getCpf() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", enderecoCompleto='" + enderecoCompleto + '\'' +
                '}';
    }


    public boolean editar(Hospede hospede) {
        try {
            File inputFile = new File("hospede.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(hospede.getCpf())) {
                    writer.write(hospede.getCpf() + "," + hospede.getNome() + "," + hospede.getEmail() + "," + hospede.getEnderecoCompleto());
                    writer.newLine();
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.err.println("Erro: Não foi possível editar Hospede");
                return false;
            }

            return true; 
        } catch (IOException e) {
            System.err.println("Erro ao editar Hospede: " + e.getMessage());
            return false;
        }
    }

    public Hospede consultar(String cpf) {
        try (BufferedReader reader = new BufferedReader(new FileReader("hospede.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(cpf)) {
                    return new Hospede(parts[0], parts[1], parts[2], parts[3]);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Hospede> listar() {
        ArrayList<Hospede> hospedes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("hospede.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                hospedes.add(new Hospede(parts[0], parts[1], parts[2], parts[3]));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: "  + e.getMessage());
        }
        return hospedes;
    }

    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }

    public void setEnderecoCompleto(String enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }
}
