package gestao.Resources;

import gestao.Exceptions.PacienteSemCheckoutException;
import gestao.models.Paciente.HistoricoPaciente;
import gestao.models.Paciente.Paciente;
import gestao.repository.HistoricoPacienteRepository;
import gestao.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class HistoricoPacienteResource {

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    HistoricoPacienteRepository historicoRepository;

    @PostMapping("/paciente/{cpf}/checkin") //Realiza checkin
    public HistoricoPaciente checkin(@RequestBody HistoricoPaciente historico, @PathVariable(value = "cpf") Long cpf) throws PacienteSemCheckoutException {

        Paciente paciente = pacienteRepository.findByCpf(cpf);
        if(paciente.isEmAtendimento()) throw new PacienteSemCheckoutException("Paciente já está em atendimento");
        paciente.setEmAtendimento(true);
        historico.setPaciente(paciente);
        historico.setHospital(paciente.getHospital());
        historico.setDataEntradaHospital(LocalDateTime.now());
        paciente.setUltimoCheckin(historico.getDataEntradaHospital());
        pacienteRepository.saveAndFlush(paciente);
        historicoRepository.saveAndFlush(historico);
        return null;

    }

    @PutMapping("/paciente/{cpf}/internado") //Define leito de internação para o atendimento atual
    public HistoricoPaciente internado(@RequestBody HistoricoPaciente historico, @PathVariable(value = "cpf") Long cpf) {

        Paciente paciente = pacienteRepository.findByCpf(cpf);
        HistoricoPaciente historicoPaciente = historicoRepository.findByDataEntradaHospital(paciente.getUltimoCheckin());
        historicoPaciente.setLeito(historico.getLeito());
        historicoRepository.saveAndFlush(historicoPaciente);
        return null;

    }

    @PutMapping("/paciente/{cpf}/checkout") // Realiza checkout
    public HistoricoPaciente checkout(@RequestBody HistoricoPaciente historico, @PathVariable(value = "cpf") Long cpf) throws PacienteSemCheckoutException {

        Paciente paciente = pacienteRepository.findByCpf(cpf);
        if(!paciente.isEmAtendimento()) throw new PacienteSemCheckoutException("Paciente não deu entrada no hospital");
        paciente.setEmAtendimento(false);
        historico.setPaciente(paciente);
        HistoricoPaciente historicoPaciente = historicoRepository.findByDataEntradaHospital(paciente.getUltimoCheckin());
        historicoPaciente.setDataSaidaHospital(LocalDateTime.now());
        pacienteRepository.saveAndFlush(paciente);
        historicoRepository.saveAndFlush(historicoPaciente);
        return null;

    }


}
