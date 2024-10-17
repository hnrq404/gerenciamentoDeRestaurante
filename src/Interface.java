import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Interface {
    private JFrame frame;
    private JTextArea outputArea;
    private List<Mesa> mesas;
    private int numMesas;

    public Interface(int numMesas) {
        this.numMesas = numMesas;
        mesas = new ArrayList<>();
        for (int i = 1; i <= numMesas; i++) {
            mesas.add(new Mesa(i));
        }
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Sistema de Restaurante");
        frame.setBounds(100, 100, 450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        JButton ocuparMesaBtn = new JButton("Ocupar Mesa");
        ocuparMesaBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ocuparMesa();
            }
        });
        panel.add(ocuparMesaBtn);

        JButton fazerPedidoBtn = new JButton("Fazer Pedido");
        fazerPedidoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fazerPedido();
            }
        });
        panel.add(fazerPedidoBtn);

        JButton fecharContaBtn = new JButton("Fechar Conta");
        fecharContaBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fecharConta();
            }
        });
        panel.add(fecharContaBtn);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // Método para ocupar a mesa
    private void ocuparMesa() {
        boolean mesaOcupada = false;
        for (Mesa mesa : mesas) {
            if (!mesa.isOcupada()) {
                mesa.ocuparMesa();
                outputArea.append("Mesa " + mesa.getNumero() + " agora está ocupada.\n");
                mesaOcupada = true;
                break;
            }
        }
        if (!mesaOcupada) {
            outputArea.append("Todas as mesas estão ocupadas.\n");
        }
    }

    // Método para fazer um pedido
    private void fazerPedido() {
        String mesaStr = JOptionPane.showInputDialog("Para qual mesa deseja fazer o pedido?");
        int mesaNumero = Integer.parseInt(mesaStr);

        if (mesaNumero <= numMesas && mesas.get(mesaNumero - 1).isOcupada()) {
            String nomeItem = JOptionPane.showInputDialog("Informe o nome do item:");
            String precoStr = JOptionPane.showInputDialog("Informe o preço do item:");
            double precoItem = Double.parseDouble(precoStr);
            String quantidadeStr = JOptionPane.showInputDialog("Informe a quantidade:");
            int quantidade = Integer.parseInt(quantidadeStr);

            ItemDoPedido item = new ItemDoPedido(nomeItem, precoItem, quantidade);
            mesas.get(mesaNumero - 1).adicionarItemPedido(item);
            outputArea.append("Item adicionado ao pedido da mesa " + mesaNumero + ".\n");
        } else {
            outputArea.append("Mesa inválida ou não ocupada.\n");
        }
    }

    // Método para fechar a conta
    private void fecharConta() {
        String mesaStr = JOptionPane.showInputDialog("Qual mesa deseja fechar a conta?");
        int mesaNumero = Integer.parseInt(mesaStr);

        if (mesaNumero <= numMesas && mesas.get(mesaNumero - 1).isOcupada()) {
            Mesa mesa = mesas.get(mesaNumero - 1);
            outputArea.append("Itens pedidos pela mesa " + mesa.getNumero() + ":\n");
            Pedido pedido = mesa.getPedido();
            List<ItemDoPedido> itens = pedido.getItens();
            for (ItemDoPedido itemPedido : itens) {
                outputArea.append(itemPedido.getQuantidade() + "x " + itemPedido.getItem() + " - R$ " + itemPedido.getPreco() + " cada\n");
            }

            double total = mesa.fecharConta();
            outputArea.append("Total a pagar da mesa " + mesa.getNumero() + " : R$ " + total + "\n");
            outputArea.append("Mesa " + mesa.getNumero() + " desocupada.\n");
        } else {
            outputArea.append("Mesa inválida ou não ocupada.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Interface window = new Interface(5);  // Pode mudar o número de mesas conforme necessário
            }
        });
    }
}
