import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ConsumoServico {
    private Servico servico;
    private Reserva reserva;
    private int quantidadeSolicitada;
    private Date dataServico;

   
    public ConsumoServico(Servico servico, Reserva reserva, int quantidadeSolicitada, Date dataServico) {
        this.servico = servico;
        this.reserva = reserva;
        this.quantidadeSolicitada = quantidadeSolicitada;
        this.dataServico = dataServico;
    }

    public ConsumoServico() {
        this.servico = null;
        this.reserva = null;
        this.quantidadeSolicitada = 0;
        this.dataServico = null;
    }

    
    public boolean cadastrar(ConsumoServico consumoServico) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("consumo_servico.txt", true))) {
            writer.println(consumoServico.getServico().getCodigo() + "," + consumoServico.getReserva().getCodigo() + "," + consumoServico.getQuantidadeSolicitada() + "," + consumoServico.getDataServico());
            return true;
        } catch (IOException e) {
            System.err.println("Error ao escrever arquivo: " + e.getMessage());
            return false;
        }
    }

    @Override
public String toString() {
    return "ConsumoServico{" +
            "servico=" + servico +
            ", reserva=" + reserva +
            ", quantidadeSolicitada=" + quantidadeSolicitada +
            ", dataServico=" + dataServico +
            '}';
}

    
public boolean editar(ConsumoServico consumoServico) {
    try {
        File inputFile = new File("consumo_servico.txt");
        File tempFile = new File("temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (Integer.parseInt(parts[0]) == consumoServico.getServico().getCodigo() && Integer.parseInt(parts[1]) == consumoServico.getReserva().getCodigo()) {
                writer.write(consumoServico.getServico().getCodigo() + "," + consumoServico.getReserva().getCodigo() + "," + consumoServico.getQuantidadeSolicitada() + "," + consumoServico.getDataServico());
                writer.newLine();
            } else {
                writer.write(line);
                writer.newLine();
            }
        }

        reader.close();
        writer.close();

        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            System.err.println("Erro: Não foi possível editar ConsumoServico.");
            return false;
        }

        return true;
    } catch (IOException e) {
        System.err.println("Erro ao editar ConsumoServico: " + e.getMessage());
        return false;
    }
}

    
    public ConsumoServico consultar(Servico servico, Reserva reserva) {
        try (BufferedReader reader = new BufferedReader(new FileReader("consumo_servico.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == servico.getCodigo() && Integer.parseInt(parts[1]) == reserva.getCodigo()) {
                    int quantidadeSolicitada = Integer.parseInt(parts[2]);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    Date dataServico = dateFormat.parse(parts[3]);
                    return new ConsumoServico(servico, reserva, quantidadeSolicitada, dataServico);
                }
            }
        } catch (IOException | ParseException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return null;
    }

    
      public ArrayList<ConsumoServico> listar() {
        ArrayList<ConsumoServico> consumoServicos = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

        try (BufferedReader reader = new BufferedReader(new FileReader("consumo_servico.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int codigoServico = Integer.parseInt(parts[0]);
                int codigoReserva = Integer.parseInt(parts[1]);
                int quantidadeSolicitada = Integer.parseInt(parts[2]);
                Date dataServico = dateFormat.parse(parts[3]); 
                Servico servico = new Servico(codigoServico, "Teste Servico", 0.0); 
                Reserva reserva = new Reserva(codigoReserva, null, null, null, null, null, null, null, null, 0.0, 0.0); // Replace with actual reserva lookup
                consumoServicos.add(new ConsumoServico(servico, reserva, quantidadeSolicitada, dataServico));
            }
        } catch (IOException | ParseException e) {
            System.err.println("Error ao ler o arquivo: " + e.getMessage());
        }
        return consumoServicos;
    }

    
    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public int getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }

    public void setQuantidadeSolicitada(int quantidadeSolicitada) {
        this.quantidadeSolicitada = quantidadeSolicitada;
    }

    public Date getDataServico() {
        return dataServico;
    }

    public void setDataServico(Date dataServico) {
        this.dataServico = dataServico;
    }
}