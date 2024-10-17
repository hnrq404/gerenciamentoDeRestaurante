public class ItemDoPedido {
    private String item;
    private double preco;
    private int quantidade;
 
    public ItemDoPedido(String item, double preco, int quantidade) {
        this.item = item;
        this.preco = preco;
        this.quantidade = quantidade;
    }
 
    public double calcularTotal() {
        return preco * quantidade;
    }
 
    public String getItem() {
        return item;
    }
 
    public double getPreco() {
        return preco;
    }
 
    public int getQuantidade() {
        return quantidade;
    }
}