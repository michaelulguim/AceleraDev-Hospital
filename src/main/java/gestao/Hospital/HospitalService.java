package gestao.Hospital;

import gestao.BancoDeSangue.BancoDeSangueENUM;
import gestao.Hospital.Hospital;
import gestao.Hospital.HospitalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public List<Hospital> findAll() {
        return this.hospitalRepository.findAll();
    }

    public Hospital create(Hospital hospital) {
        Map<BancoDeSangueENUM, Integer> bancoDeSangue = new HashMap<BancoDeSangueENUM, Integer>();
        bancoDeSangue.put(BancoDeSangueENUM.A_P, 0);
        bancoDeSangue.put(BancoDeSangueENUM.A_N, 0);
        bancoDeSangue.put(BancoDeSangueENUM.B_P, 0);
        bancoDeSangue.put(BancoDeSangueENUM.B_N, 0);
        bancoDeSangue.put(BancoDeSangueENUM.AB_P, 0);
        bancoDeSangue.put(BancoDeSangueENUM.AB_N, 0);
        bancoDeSangue.put(BancoDeSangueENUM.O_P, 0);
        bancoDeSangue.put(BancoDeSangueENUM.O_N, 0);
        hospital.setBancoDeSangue(bancoDeSangue);

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://viacep.com.br/ws/"+ hospital.getCep()+"/json";
        hospital = restTemplate.getForObject(url, Hospital.class);
        return this.hospitalRepository.save(hospital);
    }

    public Optional<Hospital> find(Long id) {
        return this.hospitalRepository.findById(id);
    }

    public boolean update(Long id, Hospital hospital) {
        if (! this.hospitalRepository.findById(id).isPresent()) {
            return false;
        }

      /*  Hospital record = new Hospital();
        record.setNome(hospital.getNome());
        record.setN_leitos(hospital.getN_leitos());
        record.setEndereco(hospital.getEndereco())
        this.hospitalRepository.save(record);*/
        return true;
    }

    public boolean delete(Long id) {
        if (! this.hospitalRepository.findById(id).isPresent()) {
            return false;
        }
        this.hospitalRepository.deleteById(id);
        return true;
    }
}
