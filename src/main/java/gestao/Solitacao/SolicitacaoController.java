package gestao.Solitacao;

import gestao.BancoDeSangue.BancoDeSangue;
import gestao.BancoDeSangue.BancoDeSangueENUM;
import gestao.Produto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SolicitacaoController {

    @Autowired
    BancoDeSangueSolicitacaoService bancoDeSangueSolicitacaoService;

    @GetMapping("/hospital/{id}/bancodesangue/{tipo}/{quantidade}")
    public void solicitarSangue(@PathVariable(value = "id") long id, @PathVariable(value = "tipo") BancoDeSangueENUM tipo, @PathVariable(value = "quantidade") Integer quantidade) {
        bancoDeSangueSolicitacaoService.solicitarSangue(id, tipo, quantidade);
       //Falto colocar a resposta HTTP. Não consegui fazer aqui
    }


}
