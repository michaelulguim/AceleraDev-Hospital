package gestao.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import gestao.models.Produto;
import gestao.respositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> getProdutos() {
        return Collections.unmodifiableList(produtoRepository.findAll());
    }

    public Optional<Produto> find(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto create(Produto p){
        return this.produtoRepository.save(p);
    }
    
    public boolean adicionar(@Valid Produto produto, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return false;
        }
        produtoRepository.save(produto);
        return true;
    }

    public boolean update(Long id, @Valid Produto produto, BindingResult resultado) {
        if(!produtoRepository.existsById(id) || resultado.hasErrors()){
            return false;
        }
        Produto p = produtoRepository.findById(id).get();
        p.setNome(produto.getNome());
        p.setDescricao(produto.getDescricao());
        p.setQuantidade(produto.getQuantidade());
        produtoRepository.save(p);
        return true;
    }

    public boolean delete(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
