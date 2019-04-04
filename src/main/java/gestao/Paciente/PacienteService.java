package gestao.Paciente;

import gestao.HistoricoPaciente.HistoricoPaciente;
//import gestao.Paciente.Paciente.HistoricoPaciente;
//import gestao.Paciente.Paciente.Paciente;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
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


    public boolean salvaPaciente(Paciente paciente, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return false;
        }
        paciente.setCpf(paciente.getCpf().replaceAll(Pattern.quote("."), "").replaceAll(("-"), ""));
        pacienteRepository.save(paciente);
        return true;
    }

    public List<Paciente> buscaTodosPaciente() {
        List<Paciente> listaPacientes = pacienteRepository.findAll();
        return listaPacientes;
    }

    public Optional<Paciente> buscaPacientePorCpf(String cpf) {
        cpf = cpf.replaceAll(Pattern.quote("."), "").replaceAll(("-"), "");
        Optional<Paciente> optional = null;
        try {
            Paciente paciente = pacienteRepository.findByCpf(cpf);
            optional = pacienteRepository.findById(paciente.getId());    //Optional só funciona para busca pelo ID. Neste caso, a busca é pelo CPF
            return optional;
        } catch (Exception ex) {
            return optional;
        }
    }

    public List<HistoricoPaciente> historicoPaciente(String cpf) {
        try {
            Paciente paciente = pacienteRepository.findByCpf(cpf);
            return paciente.pegaHistoricoPaciente();
        } catch (Exception ex) {
            return null;
        }
    }


}
