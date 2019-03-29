package gestao.controllers;

import gestao.models.Produto;
import gestao.services.ProdutoService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/estoque")
public class EstoqueController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> produtos = produtoService.getProdutos();
        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> find(@PathVariable(value = "id") Long id) {
        Optional<Produto> produto = produtoService.find(id);
        if (produto.isPresent()) {
            return ResponseEntity.ok().body(produto.get());
        }
        return ResponseEntity.notFound().build();
    }

    /*
    @GetMapping("/testePost")
    public void adt(){
        Produto p = new Produto();
        p.setNome("Tes");
        p.setDescricao("Tdes");
        p.setQuantidade(2);
        BindingResult resultado = null;
        this.atualizar(11L, p);
    }
     */

    @PostMapping
    public ResponseEntity<String> add(@RequestBody @Valid Produto produto, BindingResult resultado) {
        if (produtoService.adicionar(produto, resultado)) {
            return ResponseEntity.ok().body("Adicionado");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable(value = "id") Long id, @RequestBody @Valid Produto produto, BindingResult resultado) {
        if (produtoService.atualizar(id, produto, resultado)) {
            return ResponseEntity.ok().body("Atualizado");
        }
        return ResponseEntity.badRequest().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        if (produtoService.deletar(id)) {
            return ResponseEntity.ok().body("Deletado");
        }
        return ResponseEntity.notFound().build();
    }

}
