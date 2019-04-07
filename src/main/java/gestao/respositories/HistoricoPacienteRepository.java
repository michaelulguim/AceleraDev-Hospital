package gestao.respositories;

//import gestao.models.Paciente.HistoricoPaciente;
//import gestao.models.Paciente.Paciente;
import gestao.models.HistoricoPaciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface HistoricoPacienteRepository extends JpaRepository<HistoricoPaciente, Long> {
    HistoricoPaciente findByDataEntradaHospital(LocalDateTime id);

}
