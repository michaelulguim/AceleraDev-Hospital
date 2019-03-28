package gestao.services;

import gestao.entities.Hospital;
import gestao.repositories.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {

    @Autowired
    HospitalRepository hospitalRepository;

    public List<Hospital> findAll() {
        return this.hospitalRepository.findAll();
    }

    public void create(Hospital hospital) {
        this.hospitalRepository.save(hospital);
    }

    public Optional<Hospital> find(Long id) {
        return this.hospitalRepository.findById(id);
    }

    public boolean update(Long id, Hospital hospital) {
        return this.hospitalRepository.findById(id)
                .map(record -> {
                    record.setNome(hospital.getNome());
                    record.setN_leitos(hospital.getN_leitos());
                    record.setEndereco(hospital.getEndereco());
                    return true;
                }).orElse(false);
    }

    public boolean delete(Long id) {
        if (! this.hospitalRepository.findById(id).isPresent()) {
            return false;
        }
        this.hospitalRepository.deleteById(id);
        return true;
    }
}
