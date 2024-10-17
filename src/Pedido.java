import java.util.ArrayList;
import java.util.List;
 
public class Pedido {
    private List<ItemDoPedido> itens;
    private boolean fechado;
 
    public Pedido() {
        this.itens = new ArrayList<>();
        this.fechado = false;
    }
 
    public void adicionarItem(ItemDoPedido item) {
        if (!fechado) {
            itens.add(item);
        } else {
            System.out.println("Pedido já foi fechado.");
        }
    }
 
    public double calcularTotal() {
        double total = 0;
        for (ItemDoPedido item : itens) {
            total += item.calcularTotal();
        }
        return total;
    }
 
    public void fecharPedido() {
        fechado = true;
    }
 
    public boolean isFechado() {
        return fechado;
    }
 
    public List<ItemDoPedido> getItens() {
        return new ArrayList<>(itens);
    }
}