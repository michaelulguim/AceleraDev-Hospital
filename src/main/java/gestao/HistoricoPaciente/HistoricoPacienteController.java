package gestao.HistoricoPaciente;

//import gestao.Paciente.Paciente.HistoricoPaciente;
import gestao.Paciente.PacienteSemCheckoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/paciente")
@CrossOrigin(origins = "*")
public class HistoricoPacienteController {

    @Autowired
    HistoricoPacienteService historicoPacienteService;

    @PostMapping("/{cpf}/checkin") //Realiza checkin
    public ResponseEntity<String> checkin(@RequestBody HistoricoPaciente historico, @PathVariable(value = "cpf") String cpf) throws PacienteSemCheckoutException {
        if(historicoPacienteService.checkin(cpf, historico)) {
            return new ResponseEntity<String>("Checkin realizado", HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{cpf}/internado") //Define leito de internação para o atendimento atual
    public ResponseEntity<String> internado(@RequestBody HistoricoPaciente historico, @PathVariable(value = "cpf") String cpf) {
        if(historicoPacienteService.internado(cpf, historico)) {
            return new ResponseEntity<String>("Internação realizada", HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{cpf}/checkout") // Realiza checkout
    public ResponseEntity<String> checkout(@RequestBody HistoricoPaciente historico, @PathVariable(value = "cpf") String cpf) throws PacienteSemCheckoutException {
        if(historicoPacienteService.checkout(cpf, historico)) {
            return new ResponseEntity<String>("Checkout realizad0", HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }






}
