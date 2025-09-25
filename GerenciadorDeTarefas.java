import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorDeTarefas {

    private static class Tarefa {
        String descricao;
        LocalDate vencimento;

        Tarefa(String descricao, LocalDate vencimento) {
            this.descricao = descricao;
            this.vencimento = vencimento;
        }

        @Override
        public String toString() {
            return descricao + " (vencimento: " + vencimento + ")";
        }
    }

    private static ArrayList<Tarefa> listaDeTarefas = new ArrayList<>();

    private static void exibirMenu() {
        System.out.println("\n========= GERENCIADOR DE TAREFAS =========");
        System.out.println("1. Adicionar tarefa");
        System.out.println("2. Remover tarefa");
        System.out.println("3. Listar tarefas");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionarTarefa(Scanner scanner) {
        System.out.print("Descrição da tarefa: ");
        String descricao = scanner.nextLine();

        System.out.print("Data de vencimento (AAAA-MM-DD): ");
        String dataStr = scanner.nextLine();

        try {
            LocalDate data = LocalDate.parse(dataStr);
            listaDeTarefas.add(new Tarefa(descricao, data));
            System.out.println("Tarefa adicionada com sucesso.");
        } catch (Exception e) {
            System.out.println("Data inválida. Use o formato: AAAA-MM-DD.");
        }
    }

    private static void removerTarefa(Scanner scanner) {
        if (listaDeTarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa para remover.");
            return;
        }

        listarTarefas();
        System.out.print("Número da tarefa a remover: ");
        
        if (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida.");
            scanner.nextLine();
            return;
        }

        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice >= 1 && indice <= listaDeTarefas.size()) {
            listaDeTarefas.remove(indice - 1);
            System.out.println("Tarefa removida com sucesso.");
        } else {
            System.out.println("Número inválido.");
        }
    }

    private static void listarTarefas() {
        if (listaDeTarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }

        System.out.println("\nLista de Tarefas:");
        for (int i = 0; i < listaDeTarefas.size(); i++) {
            System.out.println((i + 1) + ". " + listaDeTarefas.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenu();

            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida. Digite um número.");
                scanner.next();
            }

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> adicionarTarefa(scanner);
                case 2 -> removerTarefa(scanner);
                case 3 -> listarTarefas();
                case 4 -> System.out.println("Saindo... Até logo!");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);

        scanner.close();
    }
}
