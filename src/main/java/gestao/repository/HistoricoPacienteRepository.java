package gestao.repository;

import gestao.models.Paciente.HistoricoPaciente;
import gestao.models.Paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoricoPacienteRepository extends JpaRepository<HistoricoPaciente, Long> {
    HistoricoPaciente findByDataEntradaHospital(LocalDateTime id);
    List<HistoricoPaciente> findByPaciente(Paciente paciente); //Buscando o histórico do Paciente

}
