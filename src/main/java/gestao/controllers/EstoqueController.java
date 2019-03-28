package gestao.controllers;

import gestao.models.Produto;
import gestao.services.ProdutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/estoque")
public class EstoqueController {
    
    @Autowired
    ProdutoService produtoService;
    
    @GetMapping
    public ResponseEntity<List<Produto>> listar(){
        List<Produto> produtos = produtoService.getProdutos();
        if(produtos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(produtos);
    }
}
