import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {
    private static List<Mesa> mesas;

    public static void main(String[] args) {
        mesas = new ArrayList<>();
        int numMesas = 5; 
        for (int i = 1; i <= numMesas; i++) {
            mesas.add(new Mesa(i));
        }

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Ocupar uma mesa");
            System.out.println("2. Fazer um pedido");
            System.out.println("3. Fechar a conta");
            System.out.println("4. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    ocuparMesa();
                    break;
                case 2:
                    fazerPedido(scanner);
                    break;
                case 3:
                    fecharConta(scanner);
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            if (todasMesasDesocupadas()) {
                System.out.println("Todas as mesas estão desocupadas. Encerrando o sistema.");
                continuar = false;
            }
        }

        scanner.close();
    }

    private static void ocuparMesa() {
        for (Mesa mesa : mesas) {
            if (!mesa.isOcupada()) {
                mesa.ocuparMesa();
                System.out.println("Mesa " + mesa.getNumero() + " agora está ocupada.");
                return;
            }
        }
        System.out.println("Todas as mesas estão ocupadas.");
    }

    private static void fazerPedido(Scanner scanner) {
        System.out.print("Para qual mesa deseja fazer o pedido? ");
        int mesaNumero = scanner.nextInt();
        scanner.nextLine(); 

        if (mesaNumero > 0 && mesaNumero <= mesas.size() && mesas.get(mesaNumero - 1).isOcupada()) {
            System.out.print("Informe o nome do item: ");
            String nomeItem = scanner.nextLine();

            System.out.print("Informe o preço do item: ");
            double precoItem = scanner.nextDouble();

            System.out.print("Informe a quantidade: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine(); 

            ItemDoPedido item = new ItemDoPedido(nomeItem, precoItem, quantidade);
            mesas.get(mesaNumero - 1).adicionarItemPedido(item);
            System.out.println("Item adicionado ao pedido da mesa " + mesaNumero + ".");
        } else {
            System.out.println("Mesa inválida ou não ocupada.");
        }
    }

    private static void fecharConta(Scanner scanner) {
        System.out.print("Qual mesa deseja fechar a conta? ");
        int mesaNumero = scanner.nextInt();
        scanner.nextLine(); 

        if (mesaNumero > 0 && mesaNumero <= mesas.size() && mesas.get(mesaNumero - 1).isOcupada()) {
            Mesa mesa = mesas.get(mesaNumero - 1);
            System.out.println("Itens pedidos pela mesa " + mesa.getNumero() + ":");

            Pedido pedido = mesa.getPedido();
            List<ItemDoPedido> itens = pedido.getItens();
            for (ItemDoPedido itemPedido : itens) {
                System.out.println(itemPedido.getQuantidade() + "x " + itemPedido.getItem() + " - R$ " + itemPedido.getPreco() + " cada");
            }

            double total = mesa.fecharConta();
            System.out.println("Total a pagar da mesa " + mesa.getNumero() + " : R$ " + total);
        } else {
            System.out.println("Mesa inválida ou não ocupada.");
        }
    }

    private static boolean todasMesasDesocupadas() {
        for (Mesa mesa : mesas) {
            if (mesa.isOcupada()) {
                return false;
            }
        }
        return true;
    }
}
