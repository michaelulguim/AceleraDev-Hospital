package gestao.models;

import java.util.Collections;
import java.util.List;

public class Estoque {
    private List<Produto> produtos;

    public List<Produto> getProdutos() {
        return Collections.unmodifiableList(produtos);
    }

    public void adicionarProduto(Produto produto){
        produtos.add(produto);
    }
    
     public void adicionarProdutoQuantidade(Produto produto, int quantidade){
        //verificar a quantidade e lançar exceção ou não alterar
        Produto p = produtos.get(produtos.indexOf(produto));
        p.incrementaQuantidade(quantidade);    
    }
    
    public void removerProduto(Produto produto){
        produtos.remove(produto);
    }
    
    public void removerProdutoQuantidade(Produto produto, int quantidade){
        //verificar a quantidade e lançar exceção ou não alterar
        Produto p = produtos.get(produtos.indexOf(produto));
        p.decrementaQuantidade(quantidade);    
    }
    
}
