package gestao.services;


import gestao.exceptions.LeitoIndisponivelException;
import gestao.exceptions.paciente.PacienteSemCheckinException;
import gestao.models.TipoLeito;
import gestao.models.hospital.Hospital;
import gestao.models.paciente.HistoricoPaciente;
import gestao.respositories.HistoricoPacienteRepository;
import gestao.models.paciente.Paciente;
import gestao.respositories.PacienteRepository;
import gestao.exceptions.paciente.PacienteSemCheckoutException;
import gestao.respositories.hospital.HospitalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class HistoricoPacienteService {

    //  @Autowired
    private PacienteRepository pacienteRepository;

    // @Autowired
    private HistoricoPacienteRepository historicoPacienteRepository;

    private HospitalRepository hospitalRepository;

    public HistoricoPacienteService(HistoricoPacienteRepository historicoPacienteRepository, PacienteRepository pacienteRepository, HospitalRepository hospitalRepository) {
        this.historicoPacienteRepository = historicoPacienteRepository;
        this.pacienteRepository = pacienteRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public boolean checkin(String cpf, Hospital hospital) throws PacienteSemCheckoutException {
        cpf = cpf.replaceAll(Pattern.quote("."), "").replaceAll(("-"), "");
        try {
            Paciente paciente = pacienteRepository.findByCpf(cpf);
            if (paciente.isEmAtendimento()) throw new PacienteSemCheckoutException();
            paciente.setEmAtendimento(true);
            HistoricoPaciente historicoPaciente = new HistoricoPaciente();
            historicoPaciente.setPaciente(paciente);
            historicoPaciente.setHospital(hospitalRepository.findById(hospital.getId()).get());
            historicoPaciente.setDataEntradaHospital(LocalDateTime.now());
            paciente.setUltimoCheckin(historicoPaciente.getDataEntradaHospital());
            List<HistoricoPaciente> listaHistorico = paciente.pegaHistoricoPaciente();
            listaHistorico.add(historicoPaciente);
            paciente.setHistoricoPaciente(listaHistorico);
            historicoPacienteRepository.saveAndFlush(historicoPaciente);
            pacienteRepository.saveAndFlush(paciente);
            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public boolean internar(String cpf, TipoLeito tipoLeito) {
        cpf = cpf.replaceAll(Pattern.quote("."), "").replaceAll(("-"), "");
        try {
            Paciente paciente = pacienteRepository.findByCpf(cpf);
            HistoricoPaciente historicoPaciente = historicoPacienteRepository.findByDataEntradaHospital(paciente.getUltimoCheckin());
            Map<TipoLeito, Integer> leitos = historicoPaciente.getHospital().getLeitos();
            if (leitos.get(tipoLeito) > 0) {
                leitos.put(tipoLeito, (leitos.get(tipoLeito) - 1));
                Hospital hospital = historicoPaciente.getHospital();
                hospital.setLeitos(leitos);
                hospitalRepository.saveAndFlush(hospital);
                historicoPaciente.setLeito(tipoLeito);
                historicoPacienteRepository.saveAndFlush(historicoPaciente);
                return true;
            } else {
                throw new LeitoIndisponivelException();
            }

        } catch (NullPointerException ex) {
            return false;
        }
    }

    public boolean checkout(String cpf, String descricaoAtendimento) throws PacienteSemCheckinException {
        cpf = cpf.replaceAll(Pattern.quote("."), "").replaceAll(("-"), "");
        try {
            Paciente paciente = pacienteRepository.findByCpf(cpf);
            if (!paciente.isEmAtendimento())
                throw new PacienteSemCheckinException();
            paciente.setEmAtendimento(false);
            HistoricoPaciente historicoPaciente = historicoPacienteRepository.findByDataEntradaHospital(paciente.getUltimoCheckin());
            historicoPaciente.setDescricaoAtendimento(descricaoAtendimento);
            historicoPaciente.setDataSaidaHospital(LocalDateTime.now());
            pacienteRepository.saveAndFlush(paciente);
            historicoPacienteRepository.saveAndFlush(historicoPaciente);
            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }


}
