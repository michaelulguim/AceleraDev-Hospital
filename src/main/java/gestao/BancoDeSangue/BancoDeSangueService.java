package gestao.BancoDeSangue;

import gestao.Hospital.Hospital;
import gestao.Hospital.HospitalRepository;
import org.springframework.stereotype.Service;

@Service
public class BancoDeSangueService {
    private final HospitalRepository hospitalRepository;

    private final BancoDeSangueRepository bancoDeSangueRepository;

    public BancoDeSangueService(HospitalRepository hospitalRepository, BancoDeSangueRepository bancoDeSangueRepository) {
        this.hospitalRepository = hospitalRepository;
        this.bancoDeSangueRepository = bancoDeSangueRepository;
    }

    public void save(long cpf, BancoDeSangue bancoDeSangue) {
        Hospital hospital = hospitalRepository.findById(cpf).get();
        bancoDeSangue.setHospital(hospital);

        if(bancoDeSangue.getTipo().toString().trim().toUpperCase().equals("A+")){
            bancoDeSangue.setTipo(BancoDeSangueENUM.A_POSITIVO);
        } else if(bancoDeSangue.getTipo().toString().trim().toUpperCase().equals("A-")){
            bancoDeSangue.setTipo(BancoDeSangueENUM.A_NEGATIVO);
        } else if(bancoDeSangue.getTipo().toString().trim().toUpperCase().equals("B+")) {
            bancoDeSangue.setTipo(BancoDeSangueENUM.B_POSITIVO);
        } else if(bancoDeSangue.getTipo().toString().trim().toUpperCase().equals("B-")) {
            bancoDeSangue.setTipo(BancoDeSangueENUM.B_NEGATIVO);
        } else if(bancoDeSangue.getTipo().toString().trim().toUpperCase().equals("AB+")) {
            bancoDeSangue.setTipo(BancoDeSangueENUM.AB_POSITIVO);
        } else if(bancoDeSangue.getTipo().toString().trim().toUpperCase().equals("AB-")) {
            bancoDeSangue.setTipo(BancoDeSangueENUM.AB_NEGATIVO);
        } else if(bancoDeSangue.getTipo().toString().trim().toUpperCase().equals("O+")) {
            bancoDeSangue.setTipo(BancoDeSangueENUM.O_POSITIVO);
        } else if(bancoDeSangue.getTipo().toString().trim().toUpperCase().equals("O-")) {
            bancoDeSangue.setTipo(BancoDeSangueENUM.O_NEGATIVO);
        } else {
            throw new IllegalArgumentException("Tipo sanguíneo inválido");
        }

        bancoDeSangueRepository.save(bancoDeSangue);
    }
}
