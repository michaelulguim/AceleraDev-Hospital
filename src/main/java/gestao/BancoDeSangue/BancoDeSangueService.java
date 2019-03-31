package gestao.BancoDeSangue;

import gestao.Hospital.Hospital;
import gestao.Hospital.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BancoDeSangueService {
    private final HospitalRepository hospitalRepository;



    public BancoDeSangueService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public Hospital add(long cpf, BancoDeSangueENUM tipo, Integer quantidadeEmLitros) {
        Hospital hospital = hospitalRepository.findById(cpf).get();
        Integer add = hospital.getBancoDeSangue().get(tipo);
        add += quantidadeEmLitros;
        Map<BancoDeSangueENUM, Integer> bancoDeSangue = hospital.getBancoDeSangue();
        bancoDeSangue.put(tipo, add);
        hospital.setBancoDeSangue(bancoDeSangue);
        return hospitalRepository.save(hospital);
    }

    public Hospital remove(long cpf, BancoDeSangueENUM tipo, Integer quantidadeEmLitros) {
        Hospital hospital = hospitalRepository.findById(cpf).get();
        Integer sub = hospital.getBancoDeSangue().get(tipo);
        sub -= quantidadeEmLitros;
        if(sub < 0) sub = 0; //Restrição de não-negatividade
        Map<BancoDeSangueENUM, Integer> bancoDeSangue = hospital.getBancoDeSangue();
        bancoDeSangue.put(tipo, sub);
        hospital.setBancoDeSangue(bancoDeSangue);
        return hospitalRepository.save(hospital);
    }

    public Hospital reset(long cpf) {
        Hospital hospital = hospitalRepository.findById(cpf).get();
        Map<BancoDeSangueENUM, Integer> bancoDeSangue = hospital.getBancoDeSangue();
        bancoDeSangue.entrySet().stream()
                .forEach((k) -> k.setValue(0));
        hospital.setBancoDeSangue(bancoDeSangue);
        return hospitalRepository.save(hospital);
    }
}
