package gestao.BancoDeSangue;

import gestao.Hospital.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hospitais/{id}/bancodesangue")
public class BancoDeSangueController {

    @Autowired
    BancoDeSangueService bancoDeSangueService;

    @PutMapping("/adicionarSangue")
    public ResponseEntity<Hospital> add(@RequestBody BancoDeSangue bancoDeSangue, @PathVariable("id") long id) {
        if(bancoDeSangueService.adicionarSangue(id, bancoDeSangue.getTipo(), bancoDeSangue.getQuantidadeEmLitros())) {
            return ResponseEntity.ok().build();
        }
       return ResponseEntity.notFound().build();
    }

    @PutMapping("/removerSangue")
    public ResponseEntity<Hospital> remove(@RequestBody BancoDeSangue bancoDeSangue, @PathVariable("id") long id) {
        if(bancoDeSangueService.removerSangue(id, bancoDeSangue.getTipo(), bancoDeSangue.getQuantidadeEmLitros())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
