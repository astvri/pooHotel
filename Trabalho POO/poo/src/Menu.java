import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Seja bem-vindo ao Sistema de Gerenciamento de Hotel!");
        exibirMenuPrincipal();
    }

    private static void exibirMenuPrincipal() {
        while (true) {
            System.out.println("\nMenu Principal:");
            System.out.println("0. Encerrar");
            System.out.println("1. Hospede");
            System.out.println("2. Item");
            System.out.println("3. Funcionario");
            System.out.println("4. Consumo de Servico"); 

            System.out.print("\nDigite sua escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (escolha) {
                case 0:
                    System.out.println("Encerrando...");
                    return;
                case 1:
                    lidarComHospede();
                    break;
                case 2:
                    lidarComItem();
                    break;
                case 3:
                    lidarComFuncionario();
                    break;
                case 4:
                    lidarComConsumoServico(); 
                    break;
                default:
                    System.out.println("Escolha inválida. Tente novamente.");
                    break;
            }
        }
    }

    private static void lidarComHospede() {
        while (true) {
            System.out.println("\nMenu de Hospede:");
            System.out.println("1. Cadastrar");
            System.out.println("2. Editar");
            System.out.println("3. Consultar");
            System.out.println("4. Listar");
            System.out.println("0. Voltar para o Menu Principal");

            System.out.print("\nDigite sua escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (escolha) {
                case 0:
                    return; 
                case 1:
                    cadastrarHospede();
                    break;
                case 2:
                    editarHospede();
                    break;
                case 3:
                    consultarHospede();
                    break;
                case 4:
                    listarHospedes();
                    break;
                default:
                    System.out.println("Escolha inválida. Por favor, tente novamente.");
                    break;
            }
        }
    }
    
    private static void lidarComItem() {
        while (true) {
            System.out.println("\nMenu de Item:");
            System.out.println("1. Cadastrar");
            System.out.println("2. Editar");
            System.out.println("3. Consultar");
            System.out.println("4. Listar");
            System.out.println("0. Voltar para o Menu Principal");

            System.out.print("\nDigite sua escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (escolha) {
                case 0:
                    return; 
                case 1:
                    cadastrarItem();
                    break;
                case 2:
                    editarItem();
                    break;
                case 3:
                    consultarItem();
                    break;
                case 4:
                    listarItens();
                    break;
                default:
                    System.out.println("Escolha inválida. Por favor, tente novamente.");
                    break;
            }
        }
    }

    private static void lidarComFuncionario() {
        while (true) {
            System.out.println("\nMenu de Funcionário:");
            System.out.println("1. Cadastrar");
            System.out.println("2. Editar");
            System.out.println("3. Consultar");
            System.out.println("4. Listar");
            System.out.println("0. Voltar para o Menu Principal");
    
            System.out.print("\nDigite sua escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (escolha) {
                case 0:
                    return; 
                case 1:
                    cadastrarFuncionario();
                    break;
                case 2:
                    editarFuncionario();
                    break;
                case 3:
                    consultarFuncionario();
                    break;
                case 4:
                    listarFuncionarios();
                    break;
                default:
                    System.out.println("Escolha inválida. Por favor, tente novamente.");
                    break;
            }
        }
    }

    private static void lidarComConsumoServico() {
        while (true) {
            System.out.println("\nMenu de Consumo de Servico:");
            System.out.println("1. Cadastrar");
            System.out.println("2. Editar");
            System.out.println("3. Consultar");
            System.out.println("4. Listar");
            System.out.println("0. Voltar para o Menu Principal");

            System.out.print("\nDigite sua escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (escolha) {
                case 0:
                    return; 
                case 1:
                    cadastrarConsumoServico();
                    break;
                case 2:
                    editarConsumoServico();
                    break;
                case 3:
                    consultarConsumoServico();
                    break;
                case 4:
                    listarConsumoServico();
                    break;
                default:
                    System.out.println("Escolha inválida. Por favor, tente novamente.");
                    break;
            }
        }
    }

    private static void cadastrarHospede() {
        System.out.println("\nCadastro de Hospede:");
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite o endereço completo: ");
        String enderecoCompleto = scanner.nextLine();

        Hospede hospede = new Hospede(cpf, nome, email, enderecoCompleto);
        boolean cadastrado = hospede.cadastrar(hospede);
        if (cadastrado) {
            System.out.println("Hospede cadastrado com sucesso.");
        } else {
            System.out.println("Erro ao cadastrar hospede.");
        }
    }

    private static void editarHospede() {
        System.out.println("\nEdição de Hospede:");
        System.out.print("Digite o CPF do hospede a ser editado: ");
        String cpf = scanner.nextLine();

        Hospede hospede = new Hospede().consultar(cpf);
        if (hospede != null) {
            System.out.print("Digite o novo endereço completo: ");
            String novoEndereco = scanner.nextLine();
            hospede.setEnderecoCompleto(novoEndereco);
            boolean editado = hospede.editar(hospede);
            if (editado) {
                System.out.println("Hospede editado com sucesso.");
            } else {
                System.out.println("Erro ao editar hospede.");
            }
        } else {
            System.out.println("Hospede não encontrado.");
        }
    }

    private static void consultarHospede() {
        System.out.println("\nConsulta de Hospede:");
        System.out.print("Digite o CPF do hospede a ser consultado: ");
        String cpf = scanner.nextLine();

        Hospede hospede = new Hospede().consultar(cpf);
        if (hospede != null) {
            System.out.println(hospede);
        } else {
            System.out.println("Hospede não encontrado.");
        }
    }

    private static void listarHospedes() {
        System.out.println("\nListagem de Hospedes:");
        ArrayList<Hospede> hospedes = new Hospede().listar();
        for (Hospede hospede : hospedes) {
            System.out.println(hospede);
        }
    }

    private static void cadastrarItem() {
        System.out.println("\nCadastro de Item:");
        System.out.print("Digite o código do item: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Digite a descrição do item: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite o valor do item: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); 
    
        Item item = new Item(codigo, descricao, valor);
        boolean cadastrado = item.cadastrar(item);
        if (cadastrado) {
            System.out.println("Item cadastrado com sucesso.");
        } else {
            System.out.println("Erro ao cadastrar item.");
        }
    }
    
    private static void editarItem() {
        System.out.println("\nEdição de Item:");
        System.out.print("Digite o código do item a ser editado: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); 
    
        Item item = new Item().consultar(codigo);
        if (item != null) {
            System.out.print("Digite a nova descrição do item: ");
            String novaDescricao = scanner.nextLine();
            System.out.print("Digite o novo valor do item: ");
            double novoValor = scanner.nextDouble();
            scanner.nextLine(); 
            
            item.setDescricao(novaDescricao);
            item.setValor(novoValor);
            boolean editado = item.editar(item);
            if (editado) {
                System.out.println("Item editado com sucesso.");
            } else {
                System.out.println("Erro ao editar item.");
            }
        } else {
            System.out.println("Item não encontrado.");
        }
    }
    
    private static void consultarItem() {
        System.out.println("\nConsulta de Item:");
        System.out.print("Digite o código do item a ser consultado: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); 
    
        Item item = new Item().consultar(codigo);
        if (item != null) {
            System.out.println(item);
        } else {
            System.out.println("Item não encontrado.");
        }
    }
    
    private static void listarItens() {
        System.out.println("\nListagem de Itens:");
        ArrayList<Item> itens = new Item().listar();
        for (Item item : itens) {
            System.out.println(item);
        }
    }

    private static void cadastrarFuncionario() {
        System.out.println("\nCadastro de Funcionário:");
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite o setor: ");
        String setor = scanner.nextLine();
    
        Funcionario funcionario = new Funcionario(cpf, nome, email, setor);
        boolean cadastrado = funcionario.cadastrar(funcionario);
        if (cadastrado) {
            System.out.println("Funcionário cadastrado com sucesso.");
        } else {
            System.out.println("Erro ao cadastrar funcionário.");
        }
    }
    
    private static void editarFuncionario() {
        System.out.println("\nEdição de Funcionário:");
        System.out.print("Digite o CPF do funcionário a ser editado: ");
        String cpf = scanner.nextLine();
    
        Funcionario funcionario = new Funcionario().consultar(cpf);
        if (funcionario != null) {
            System.out.print("Digite o novo setor: ");
            String novoSetor = scanner.nextLine();
            funcionario.setSetor(novoSetor);
            boolean editado = funcionario.editar(funcionario);
            if (editado) {
                System.out.println("Funcionário editado com sucesso.");
            } else {
                System.out.println("Erro ao editar funcionário.");
            }
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }
    
    private static void consultarFuncionario() {
        System.out.println("\nConsulta de Funcionário:");
        System.out.print("Digite o CPF do funcionário a ser consultado: ");
        String cpf = scanner.nextLine();
    
        Funcionario funcionario = new Funcionario().consultar(cpf);
        if (funcionario != null) {
            System.out.println(funcionario);
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }
    
    private static void listarFuncionarios() {
        System.out.println("\nListagem de Funcionários:");
        ArrayList<Funcionario> funcionarios = new Funcionario().listar();
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
    }

   private static void cadastrarConsumoServico() {
        System.out.println("\nCadastro de Consumo de Servico:");

        
        System.out.print("Digite o código do serviço: ");
        int codigoServico = scanner.nextInt();
        scanner.nextLine(); 

        
        System.out.print("Digite o código da reserva: ");
        int codigoReserva = scanner.nextInt();
        scanner.nextLine(); 

        
        System.out.print("Digite a quantidade solicitada: ");
        int quantidadeSolicitada = scanner.nextInt();
        scanner.nextLine(); 

        
        System.out.print("Digite a data do serviço (formato: dd/MM/yyyy): ");
        String dataString = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataServico;
        try {
            dataServico = dateFormat.parse(dataString);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Use o formato dd/MM/yyyy.");
            return;
        }

        
        Servico servico = new Servico(codigoServico, null, 0.0);
        Reserva reserva = new Reserva(codigoReserva, null, null, null, null, null, null, null, null, 0.0, 0.0);
        ConsumoServico consumoServico = new ConsumoServico(servico, reserva, quantidadeSolicitada, dataServico);

        
        boolean cadastrado = consumoServico.cadastrar(consumoServico);
        if (cadastrado) {
            System.out.println("Consumo de Serviço cadastrado.");
        } else {
            System.out.println("Erro ao cadastrar Consumo de Serviço.");
        }
    }

    private static void editarConsumoServico() {
        try {
            System.out.println("\nEdição de Consumo de Serviço:");
    
            
            System.out.print("Digite o código do serviço: ");
            int codigoServico = scanner.nextInt();
            scanner.nextLine(); 
    
            
            System.out.print("Digite o código da reserva: ");
            int codigoReserva = scanner.nextInt();
            scanner.nextLine(); 
    
            
            ConsumoServico consumoServico = new ConsumoServico().consultar(new Servico(codigoServico, null, 0.0), new Reserva(codigoReserva, null, null, null, null, null, null, null, null, 0.0, 0.0));
            if (consumoServico == null) {
                System.out.println("Consumo de Serviço não encontrado.");
                return;
            }
    
            
            System.out.print("Digite a nova quantidade solicitada: ");
            int novaQuantidade = scanner.nextInt();
            scanner.nextLine(); 
    
            
            consumoServico.setQuantidadeSolicitada(novaQuantidade);
    
            
            boolean editado = consumoServico.editar(consumoServico);
            if (editado) {
                System.out.println("Consumo de Serviço editado com sucesso.");
            } else {
                System.out.println("Erro ao editar Consumo de Serviço.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao editar Consumo de Serviço: " + e.getMessage());
        }
    }
    
    private static void consultarConsumoServico() {
        try {
            System.out.println("\nConsulta de Consumo de Serviço:");
    
            
            System.out.print("Digite o código do serviço: ");
            int codigoServico = scanner.nextInt();
            scanner.nextLine(); 
    
            
            System.out.print("Digite o código da reserva: ");
            int codigoReserva = scanner.nextInt();
            scanner.nextLine(); 
    
            
            ConsumoServico consumoServico = new ConsumoServico().consultar(new Servico(codigoServico, null, 0.0), new Reserva(codigoReserva, null, null, null, null, null, null, null, null, 0.0, 0.0));
            if (consumoServico != null) {
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                
                String dataString = dateFormat.format(consumoServico.getDataServico());
                
                System.out.println("Servico: " + consumoServico.getServico().getCodigo() + ", Reserva: " + consumoServico.getReserva().getCodigo() +
                        ", Quantidade Solicitada: " + consumoServico.getQuantidadeSolicitada() + ", Data do Serviço: " + dataString);
            } else {
                System.out.println("Consumo de Serviço não encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao consultar Consumo de Serviço: " + e.getMessage());
        }
    }
    private static void listarConsumoServico() {
        System.out.println("\nListagem de Consumo de Servico:");
    
        ConsumoServico consumoServico = new ConsumoServico();
        ArrayList<ConsumoServico> consumoServicos = consumoServico.listar();
    
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
        for (ConsumoServico cs : consumoServicos) {
            
            String dataString = dateFormat.format(cs.getDataServico());
            
            System.out.println("Servico: " + cs.getServico().getCodigo() + ", Reserva: " + cs.getReserva().getCodigo() +
                    ", Quantidade Solicitada: " + cs.getQuantidadeSolicitada() + ", Data do Servico: " + dataString);
        }
    }
}
