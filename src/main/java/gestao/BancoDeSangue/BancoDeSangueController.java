package gestao.BancoDeSangue;

import gestao.Hospital.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hospital/{id}/bancodesangue")
public class BancoDeSangueController {

    @Autowired
    BancoDeSangueService bancoDeSangueService;

    @PutMapping("/add")
    public Hospital add(@RequestBody BancoDeSangue bancoDeSangue, @PathVariable("id") long id) {
       return bancoDeSangueService.add(id, bancoDeSangue.getTipo(), bancoDeSangue.getQuantidadeEmLitros());
    }

    @PutMapping("/remove")
    public Hospital remove(@RequestBody BancoDeSangue bancoDeSangue, @PathVariable("id") long id) {
        return bancoDeSangueService.remove(id, bancoDeSangue.getTipo(), bancoDeSangue.getQuantidadeEmLitros());
    }

    @PutMapping("/reset")
    public Hospital reset(@PathVariable("id") long id) {
        return bancoDeSangueService.reset(id);
    }
}
