package gestao.Paciente;


import gestao.HistoricoPaciente.HistoricoPaciente;
//import gestao.Paciente.Paciente.HistoricoPaciente;
//import gestao.Paciente.Paciente.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/paciente")
@CrossOrigin(origins = "*")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @PostMapping//Salva um paciente
    public ResponseEntity<Paciente> salvaPaciente(@RequestBody @Valid Paciente paciente, BindingResult resultado) {
        if (pacienteService.salvaPaciente(paciente, resultado)) {
            return new ResponseEntity<Paciente>(paciente, HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping //Retorna Lista de todos os pacientes
    public ResponseEntity<List<Paciente>> listaPacientes() {
        return new ResponseEntity<List<Paciente>>(pacienteService.buscaTodosPaciente(), HttpStatus.FOUND);
    }

    @GetMapping("/{cpf}") //Retorna um paciente pelo CPF
    public ResponseEntity<Paciente> paciente(@PathVariable(value = "cpf") String cpf) {
        Optional<Paciente> paciente = pacienteService.buscaPacientePorCpf(cpf);
        if(paciente.isPresent()) {
            return new ResponseEntity<Paciente>(paciente.get(), HttpStatus.FOUND);
        } else {
           return  ResponseEntity.notFound().build();
        }
    }

    @PutMapping //Atualiza um paciente passado por parâmetro
    public ResponseEntity<Paciente> atualizaPaciente(@RequestBody @Valid Paciente paciente, BindingResult resultado) {
        if (pacienteService.salvaPaciente(paciente, resultado)) {
            return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{cpf}/historico") //Retorna o histórico de um paciente pelo CPF
    public ResponseEntity<List<HistoricoPaciente>> historicoPaciente(@PathVariable(value = "cpf") String cpf) {
        List<HistoricoPaciente> historicoPacientes = pacienteService.historicoPaciente(cpf);
        if (historicoPacientes != null) {
            return new ResponseEntity<List<HistoricoPaciente>>(historicoPacientes, HttpStatus.FOUND);
        } else {
            return  ResponseEntity.notFound().build();
        }
    }


}
