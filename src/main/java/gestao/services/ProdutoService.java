package gestao.services;

import gestao.models.Produto;
import gestao.repositories.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> getProdutos(){
        return produtoRepository.findAll();
    }
    
}
