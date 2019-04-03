package gestao.Paciente;

import gestao.HistoricoPaciente.HistoricoPaciente;
//import gestao.Paciente.Paciente.HistoricoPaciente;
//import gestao.Paciente.Paciente.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class PacienteService {

    //  @Autowired
    private final PacienteRepository pacienteRepository;
    //  @Autowired
   // private final HistoricoPacienteRepository historicoPacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


    public Paciente salvaPaciente(Paciente paciente) {
        paciente.setCpf(paciente.getCpf().replaceAll(Pattern.quote ("."), "").replaceAll(("-"), ""));
        pacienteRepository.save(paciente);
        return paciente;
    }

    public List<Paciente> buscaTodosPaciente() {
        List<Paciente> listaPacientes = pacienteRepository.findAll();
        return listaPacientes;
    }

    public Paciente buscaPacientePorCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf);
    }

    public List<HistoricoPaciente> historicoPaciente(String cpf) {
        Paciente paciente = pacienteRepository.findByCpf(cpf);
        return pacienteRepository.findByCpf(cpf).pegaHistoricoPaciente();
    }


}
