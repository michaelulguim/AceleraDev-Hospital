package gestao.controllers;

import gestao.models.Produto;
import gestao.repositories.ProdutoRepository;
import gestao.services.ProdutoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    // ProdutoService produtoService = new ProdutoService();
    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        //List<Produto> produtos = produtoService.getProdutos();
        List<Produto> produtos = produtoRepository.findAll();
        // if(produtos==null){
        // return ResponseEntity.noContent().build();
        //}
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getUusuario(@PathVariable(value = "id") Long id) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(null);
        }
        Optional<Produto> produto = produtoRepository.findById(id);
        return ResponseEntity.ok().body(produto.get());
    }

    @PostMapping
    public ResponseEntity<String> adicionarProduto(Produto produto) {
        produtoRepository.save(produto);
        return ResponseEntity.ok().body("Produto adicionado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable("id") Long id, Produto produto) {
        if (!produtoRepository.existsById(id)) {
            //return ResponseEntity.badRequest().body("Usuario não foi cadastrado");
            return ResponseEntity.notFound().build();
        }
        Optional<Produto> produtoOpt = produtoRepository.findById(id);
        Produto produtoCadastrado = produtoOpt.get();
        produtoCadastrado.setNome(produto.getNome());
        produtoCadastrado.setDescrição(produto.getDescrição());
        produtoRepository.save(produtoCadastrado);
        //return ResponseEntity.ok().build();
        return ResponseEntity.ok().body("Produto atualizado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable(value = "id") Long id) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Produto não foi encontrado");
        }
        Optional<Produto> produto = produtoRepository.findById(id);
        produtoRepository.delete(produto.get());
        return ResponseEntity.ok().body("Produto excluido");
    }

}
