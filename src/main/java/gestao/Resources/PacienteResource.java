package gestao.Resources;


import gestao.models.Paciente.HistoricoPaciente;
import gestao.models.Paciente.Paciente;
import gestao.repository.HistoricoPacienteRepository;
import gestao.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class PacienteResource {

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    HistoricoPacienteRepository historicoPacienteRepository;

    @PostMapping("/paciente")
    public Paciente salvaPaciente(@RequestBody Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @GetMapping("/paciente")
    public List<Paciente> listaPacientes(){
        return pacienteRepository.findAll();
    }

    @GetMapping("/paciente/{cpf}")
    public Paciente paciente(@PathVariable(value = "cpf") Long cpf){
        return pacienteRepository.findByCpf(cpf);
    }

    @PutMapping("/paciente")
    public Paciente atualizaPaciente(@RequestBody Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @GetMapping("/paciente/{cpf}/historico")
    public List<HistoricoPaciente> historicoPaciente(@PathVariable(value = "cpf") Long cpf) {
        Paciente paciente = pacienteRepository.findByCpf(cpf);
        return historicoPacienteRepository.findByPaciente(paciente);
    }

}
