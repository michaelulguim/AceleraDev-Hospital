package gestao.services;


import gestao.models.HistoricoPaciente;
import gestao.respositories.HistoricoPacienteRepository;
import gestao.models.Paciente;
import gestao.respositories.PacienteRepository;
import gestao.exceptions.paciente.PacienteSemCheckoutException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class HistoricoPacienteService {

    //  @Autowired
    private PacienteRepository pacienteRepository;

    // @Autowired
    private HistoricoPacienteRepository historicoPacienteRepository;

    public HistoricoPacienteService(HistoricoPacienteRepository historicoPacienteRepository, PacienteRepository pacienteRepository) {
        this.historicoPacienteRepository = historicoPacienteRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public boolean checkin(String cpf, HistoricoPaciente historico) throws PacienteSemCheckoutException {
        cpf = cpf.replaceAll(Pattern.quote("."), "").replaceAll(("-"), "");

        try {
            Paciente paciente = pacienteRepository.findByCpf(cpf);
            if (paciente.isEmAtendimento()) throw new PacienteSemCheckoutException("Paciente já está em atendimento");
            paciente.setEmAtendimento(true);
            historico.setPaciente(paciente);
            historico.setHospital(paciente.getHospital());
            historico.setDataEntradaHospital(LocalDateTime.now());
            paciente.setUltimoCheckin(historico.getDataEntradaHospital());
            List<HistoricoPaciente> listaHistorico = paciente.pegaHistoricoPaciente();
            listaHistorico.add(historico);
            paciente.setHistoricoPaciente(listaHistorico);
            historicoPacienteRepository.saveAndFlush(historico);
            pacienteRepository.saveAndFlush(paciente);
            return true;
        } catch (NullPointerException ex) {
            return false;
        }

    }

    public boolean internado(String cpf, HistoricoPaciente historico) {
        cpf = cpf.replaceAll(Pattern.quote("."), "").replaceAll(("-"), "");
        try {
            Paciente paciente = pacienteRepository.findByCpf(cpf);
            HistoricoPaciente historicoPaciente = historicoPacienteRepository.findByDataEntradaHospital(paciente.getUltimoCheckin());
            historicoPaciente.setLeito(historico.getLeito());
            historicoPacienteRepository.saveAndFlush(historicoPaciente);
            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public boolean checkout(String cpf, HistoricoPaciente historico) throws PacienteSemCheckoutException {
        cpf = cpf.replaceAll(Pattern.quote("."), "").replaceAll(("-"), "");
        try {
            Paciente paciente = pacienteRepository.findByCpf(cpf);
            if (!paciente.isEmAtendimento())
                 throw new PacienteSemCheckoutException("Paciente não deu entrada no hospital");
            paciente.setEmAtendimento(false);
            historico.setPaciente(paciente);
            HistoricoPaciente historicoPaciente = historicoPacienteRepository.findByDataEntradaHospital(paciente.getUltimoCheckin());
            historicoPaciente.setDataSaidaHospital(LocalDateTime.now());
            pacienteRepository.saveAndFlush(paciente);
            historicoPacienteRepository.saveAndFlush(historicoPaciente);
            return true;
        }catch(NullPointerException ex) {
            return false;
        }
    }


}
