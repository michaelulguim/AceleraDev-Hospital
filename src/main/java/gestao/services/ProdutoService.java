package gestao.services;

import gestao.models.Produto;
import gestao.repositories.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
   
    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> getProdutos(){
        return produtoRepository.findAll();
    }
    
}
