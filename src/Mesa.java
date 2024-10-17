public class Mesa {
    private int numero;
    private boolean ocupada;
    private Pedido pedido;
 
    public Mesa(int numero) {
        this.numero = numero;
        this.ocupada = false;
        this.pedido = null;
    }
 
    public int getNumero() {
        return numero;
    }
 
    public boolean isOcupada() {
        return ocupada;
    }
 
    public void ocuparMesa() {
        this.ocupada = true;
        this.pedido = new Pedido();
    }
 
    public void liberarMesa() {
        this.ocupada = false;
        this.pedido = null;
    }
 
    public Pedido getPedido() {
        return pedido;
    }
 
    public void adicionarItemPedido(ItemDoPedido item) {
        if (ocupada) {
            pedido.adicionarItem(item);
        } else {
            System.out.println("Não tem ninguém nesta mesa.");
        }
    }
 
    public double fecharConta() {
        if (ocupada && pedido != null && !pedido.isFechado()) {
            pedido.fecharPedido();
            double total = pedido.calcularTotal();
            liberarMesa();
            return total;
        } else {
            System.out.println("Não é possível fechar a conta.");
            return 0;
        }
    }
}
