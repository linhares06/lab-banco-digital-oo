import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Optional;

public class Main {
    private static List<Conta> contas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Listar Contas");
            System.out.println("2. Criar Conta");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    listContas();
                    break;
                case 2:
                    createConta();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção invalida. Tente novamente.");
            }
        }
    }

    private static void listContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta encontrada.");
        } else {
            System.out.println("\n=== Lista de Contas ===");
            for (Conta conta : contas) {
                System.out.println(String.format("Número: %s - Saldo: %.2f", conta.getNumero(), conta.getSaldo()));
            }
            menuConta();
        }
    }

    private static void menuConta() {
        while (true) {
            System.out.println("\n=== Menu de Contas ===");
            System.out.println("1. Buscar informações por número da conta");
            System.out.println("2. Fazer transações");
            System.out.println("3. Voltar");
            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    buscarInfoConta();
                    break;
                case 2:
                    menuTransacao();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opção invalida. Tente novamente.");
            }
        }
    }

    private static void menuTransacao() {
        while (true) {
            System.out.println("\n=== Menu de Transações ===");
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Voltar");
            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    depositar();
                    break;
                case 2:
                    sacar();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opção invalida. Tente novamente.");
            }
        }
    }

    private static void depositar() {
        System.out.print("Digite o número da conta: ");
        int numeroConta = scanner.nextInt();

        System.out.print("Digite o valor do depósito: ");
        double valor = scanner.nextDouble();

        Optional<Conta> conta = contas.stream().filter(c -> c.getNumero() == numeroConta).findFirst();

        conta.ifPresentOrElse(
            c -> c.depositar(valor),
            () -> System.out.println("Conta não encontrada")
        );
    }

    private static void sacar() {
        System.out.print("Digite o número da conta: ");
        int numeroConta = scanner.nextInt();

        System.out.print("Digite o valor do saque: ");
        double valor = scanner.nextDouble();

        Optional<Conta> conta = contas.stream().filter(c -> c.getNumero() == numeroConta).findFirst();

        conta.ifPresentOrElse(
            c -> c.sacar(valor),
            () -> System.out.println("Conta não encontrada")
        );
    }

    private static void buscarInfoConta() {
        System.out.print("Digite o número da conta: ");
        int numeroConta = scanner.nextInt();

        Optional<Conta> conta = contas.stream().filter(c -> c.getNumero() == numeroConta).findFirst();

        conta.ifPresentOrElse(
            c -> c.imprimirExtrato(),
            () -> System.out.println("Conta não encontrada")
        );
    }

    private static void createConta() {
        System.out.print("Insira o nome do cliente: ");
        String nome = scanner.nextLine();
        Cliente cliente = new Cliente();
        cliente.setNome(nome);

        ContaCorrente contaCorrente = new ContaCorrente(cliente);
        ContaPoupanca contaPoupanca = new ContaPoupanca(cliente);

        contas.add(contaCorrente);
        contas.add(contaPoupanca);

        System.out.println("Contas criadas com sucesso!");
    }
}
