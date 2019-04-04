package gestao.HistoricoPaciente;

import com.google.gson.Gson;
import gestao.Paciente.PacienteSemCheckoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/pacientes")
@CrossOrigin(origins = "*")
public class HistoricoPacienteController {

    private static final Gson gson = new Gson();

    @Autowired
    HistoricoPacienteService historicoPacienteService;

    @PostMapping("/{cpf}/checkin") //Realiza checkin
    public ResponseEntity<String> checkin(@RequestBody HistoricoPaciente historico, @PathVariable(value = "cpf") String cpf) throws PacienteSemCheckoutException {
        if(historicoPacienteService.checkin(cpf, historico)) {
            return new ResponseEntity<String>(gson.toJson("Checkin realizado"), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{cpf}/internar") //Define leito de internação para o atendimento atual
    public ResponseEntity<String> internado(@RequestBody HistoricoPaciente historico, @PathVariable(value = "cpf") String cpf) {
        if(historicoPacienteService.internado(cpf, historico)) {
            return new ResponseEntity<String>(gson.toJson("Internação realizada"), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{cpf}/checkout") // Realiza checkout
    public ResponseEntity<String> checkout(@RequestBody HistoricoPaciente historico, @PathVariable(value = "cpf") String cpf) throws PacienteSemCheckoutException {
        if(historicoPacienteService.checkout(cpf, historico)) {
            return new ResponseEntity<String>(gson.toJson("Checkout realizado"), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }






}
