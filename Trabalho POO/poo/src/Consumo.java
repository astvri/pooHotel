import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Consumo {
    private int codigo;
    private Item item;
    private Reserva reserva;
    private int quantidadeSolicitada;
    private Date dataConsumo;

    public Consumo(int codigo, Item item, Reserva reserva, int quantidadeSolicitada, Date dataConsumo) {
        this.codigo = codigo;
        this.item = item;
        this.reserva = reserva;
        this.quantidadeSolicitada = quantidadeSolicitada;
        this.dataConsumo = dataConsumo;
    }

    
    public boolean cadastrar(Consumo consumo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("consumo.txt", true))) {
            writer.println(consumo.getCodigo() + "," + consumo.getItem().getCodigo() + "," + consumo.getReserva().getCodigo() + "," + consumo.getQuantidadeSolicitada() + "," + consumo.getDataConsumo());
            return true;
        } catch (IOException e) {
            System.err.println("Error while writing to file: " + e.getMessage());
            return false;
        }
    }

    @Override
public String toString() {
    return "Consumo{" +
            "codigo=" + codigo +
            ", item=" + item +
            ", reserva=" + reserva +
            ", quantidadeSolicitada=" + quantidadeSolicitada +
            ", dataConsumo=" + dataConsumo +
            '}';
}

    public boolean editar(Consumo consumo) {
        try {
            File inputFile = new File("consumo.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == consumo.getCodigo()) {
                    writer.write(consumo.getCodigo() + "," + consumo.getItem().getCodigo() + "," + consumo.getReserva().getCodigo() + "," + consumo.getQuantidadeSolicitada() + "," + consumo.getDataConsumo());
                    writer.newLine();
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.err.println("Erro: Não foi possível editar Consumo.");
                return false;
            }

            return true;
        } catch (IOException e) {
            System.err.println("Erro ao editar Consumo: " + e.getMessage());
            return false;
        }
    }

    
    public Consumo consultar(int codigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("consumo.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == codigo) {
                    Item item = new Item(Integer.parseInt(parts[1]), "Test Item", "Test Description", 10.0); // Teste Item para demonstracao
                    Reserva reserva = new Reserva(Integer.parseInt(parts[2]), null, null, null, null, null, null, null, null, 0.0, 0.0); // Replace with actual reserva lookup
                    int quantidadeSolicitada = Integer.parseInt(parts[3]);
                    Date dataConsumo = new Date(parts[4]); 
                    return new Consumo(Integer.parseInt(parts[0]), item, reserva, quantidadeSolicitada, dataConsumo);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return null;
    }

   
    public ArrayList<Consumo> listar() {
        ArrayList<Consumo> consumos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("consumo.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Item item = new Item(Integer.parseInt(parts[1]), "Test Item", "Test Description", 10.0); 
                Reserva reserva = new Reserva(Integer.parseInt(parts[2]), null, null, null, null, null, null, null, null, 0.0, 0.0); // Replace with actual reserva lookup
                int quantidadeSolicitada = Integer.parseInt(parts[3]);
                Date dataConsumo = new Date(parts[4]); 
                consumos.add(new Consumo(Integer.parseInt(parts[0]), item, reserva, quantidadeSolicitada, dataConsumo));
            }
        } catch (IOException e) {
            System.err.println("Error ao ler o arquivo: " + e.getMessage());
        }
        return consumos;
    }

    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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

    public Date getDataConsumo() {
        return dataConsumo;
    }

    public void setDataConsumo(Date dataConsumo) {
        this.dataConsumo = dataConsumo;
    }
}
