import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Reserva {
    private int codigo;
    private Hospede hospede;
    private Quarto quarto;
    private Funcionario funcionarioReserva;
    private Funcionario funcionarioFechamento;
    private Date dataEntradaReserva;
    private Date dataSaidaReserva;
    private Date dataCheckin;
    private Date dataCheckout;
    private double valorReserva;
    private double valorPago;

    public Reserva(int codigo, Hospede hospede, Quarto quarto, Funcionario funcionarioReserva, Funcionario funcionarioFechamento, Date dataEntradaReserva, Date dataSaidaReserva, Date dataCheckin, Date dataCheckout, double valorReserva, double valorPago) {
        this.codigo = codigo;
        this.hospede = hospede;
        this.quarto = quarto;
        this.funcionarioReserva = funcionarioReserva;
        this.funcionarioFechamento = funcionarioFechamento;
        this.dataEntradaReserva = dataEntradaReserva;
        this.dataSaidaReserva = dataSaidaReserva;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
        this.valorReserva = valorReserva;
        this.valorPago = valorPago;
    }

    public boolean cadastrar(Reserva reserva) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("reserva.txt", true))) {
            writer.println(reserva.getCodigo() + "," + reserva.getHospede().getCpf() + "," + reserva.getQuarto().getCodigo() + "," + reserva.getFuncionarioReserva().getCpf() + "," + reserva.getFuncionarioFechamento().getCpf() + "," + reserva.getDataEntradaReserva() + "," + reserva.getDataSaidaReserva() + "," + reserva.getDataCheckin() + "," + reserva.getDataCheckout() + "," + reserva.getValorReserva() + "," + reserva.getValorPago());
            return true;
        } catch (IOException e) {
            System.err.println("Error ao escrever o arquivo" + e.getMessage());
            return false;
        }
    }

    @Override
public String toString() {
    return "Reserva{" +
            "codigo=" + codigo +
            ", hospede=" + hospede +
            ", quarto=" + quarto +
            ", funcionarioReserva=" + funcionarioReserva +
            ", funcionarioFechamento=" + funcionarioFechamento +
            ", dataEntradaReserva=" + dataEntradaReserva +
            ", dataSaidaReserva=" + dataSaidaReserva +
            ", dataCheckin=" + dataCheckin +
            ", dataCheckout=" + dataCheckout +
            ", valorReserva=" + valorReserva +
            ", valorPago=" + valorPago +
            '}';
}

    public boolean editar(Reserva reserva) {
        try {
            File inputFile = new File("reserva.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == reserva.getCodigo()) {
                    writer.write(reserva.getCodigo() + "," + reserva.getHospede().getCpf() + "," + reserva.getQuarto().getCodigo() + "," + reserva.getFuncionarioReserva().getCpf() + "," + reserva.getFuncionarioFechamento().getCpf() + "," + reserva.getDataEntradaReserva() + "," + reserva.getDataSaidaReserva() + "," + reserva.getDataCheckin() + "," + reserva.getDataCheckout() + "," + reserva.getValorReserva() + "," + reserva.getValorPago());
                    writer.newLine();
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.err.println("Erro: Não foi possível editar Reserva.");
                return false;
            }

            return true; 
        } catch (IOException e) {
            System.err.println("Erro ao editar Reserva: " + e.getMessage());
            return false;
        }
    }

  public Reserva consultar(int codigo) {
    try (BufferedReader reader = new BufferedReader(new FileReader("reserva.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (Integer.parseInt(parts[0]) == codigo) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                Hospede hospede = new Hospede(parts[1], "dummy", "dummy", "dummy"); 
                Quarto quarto = new Quarto(Integer.parseInt(parts[2]), new Categoria(0, "dummy", 0.0), "dummy"); // 
                Funcionario funcionarioReserva = new Funcionario(parts[3], "dummy", "dummy", "dummy"); 
                Funcionario funcionarioFechamento = new Funcionario(parts[4], "dummy", "dummy", "dummy"); 
                Date dataEntradaReserva = dateFormat.parse(parts[5]); 
                Date dataSaidaReserva = dateFormat.parse(parts[6]); 
                Date dataCheckin = dateFormat.parse(parts[7]); 
                Date dataCheckout = dateFormat.parse(parts[8]); 
                double valorReserva = Double.parseDouble(parts[9]);
                double valorPago = Double.parseDouble(parts[10]);
                return new Reserva(Integer.parseInt(parts[0]), hospede, quarto, funcionarioReserva, funcionarioFechamento, dataEntradaReserva, dataSaidaReserva, dataCheckin, dataCheckout, valorReserva, valorPago);
            }
        }
    } catch (IOException | ParseException e) {
        System.err.println("Erro ao ler o arquivo: " + e.getMessage());
    }
    return null;
}

public ArrayList<Reserva> listar() {
    ArrayList<Reserva> reservas = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader("reserva.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
            Hospede hospede = new Hospede(parts[1], "dummy", "dummy", "dummy"); 
            Quarto quarto = new Quarto(Integer.parseInt(parts[2]), new Categoria(0, "dummy", 0.0), "dummy"); 
            Funcionario funcionarioReserva = new Funcionario(parts[3], "dummy", "dummy", "dummy"); 
            Funcionario funcionarioFechamento = new Funcionario(parts[4], "dummy", "dummy", "dummy"); 
            Date dataEntradaReserva = dateFormat.parse(parts[5]); 
            Date dataSaidaReserva = dateFormat.parse(parts[6]); 
            Date dataCheckin = dateFormat.parse(parts[7]); 
            Date dataCheckout = dateFormat.parse(parts[8]); 
            double valorReserva = Double.parseDouble(parts[9]);
            double valorPago = Double.parseDouble(parts[10]);
            reservas.add(new Reserva(Integer.parseInt(parts[0]), hospede, quarto, funcionarioReserva, funcionarioFechamento, dataEntradaReserva, dataSaidaReserva, dataCheckin, dataCheckout, valorReserva, valorPago));
        }
    } catch (IOException | ParseException e) {
        System.err.println("Erro ao ler o arquivo: " + e.getMessage());
    }
    return reservas;
}

   
    public void pagarReserva(double valor) {
        this.valorPago += valor;
    }

    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Funcionario getFuncionarioReserva() {
        return funcionarioReserva;
    }

    public void setFuncionarioReserva(Funcionario funcionarioReserva) {
        this.funcionarioReserva = funcionarioReserva;
    }

    public Funcionario getFuncionarioFechamento() {
        return funcionarioFechamento;
    }

    public void setFuncionarioFechamento(Funcionario funcionarioFechamento) {
        this.funcionarioFechamento = funcionarioFechamento;
    }

    public Date getDataEntradaReserva() {
        return dataEntradaReserva;
    }

    public void setDataEntradaReserva(Date dataEntradaReserva) {
        this.dataEntradaReserva = dataEntradaReserva;
    }

    public Date getDataSaidaReserva() {
        return dataSaidaReserva;
    }

    public void setDataSaidaReserva(Date dataSaidaReserva) {
        this.dataSaidaReserva = dataSaidaReserva;
    }

    public Date getDataCheckin() {
        return dataCheckin;
    }

    public void setDataCheckin(Date dataCheckin) {
        this.dataCheckin = dataCheckin;
    }

    public Date getDataCheckout() {
        return dataCheckout;
    }

    public void setDataCheckout(Date dataCheckout) {
        this.dataCheckout = dataCheckout;
    }

    public double getValorReserva() {
        return valorReserva;
    }

    public void setValorReserva(double valorReserva) {
        this.valorReserva = valorReserva;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }
}