package gestao.respositories;

//import gestao.models.Paciente.Paciente;
import gestao.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByCpf(String cpf);

}
