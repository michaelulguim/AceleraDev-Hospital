package gestao.services;


import gestao.models.banco_de_sangue.BancoDeSangueFactory;
import gestao.models.leito.LeitoFactory;

import gestao.exceptions.HospitalNotFoundException;
import gestao.models.hospital.Hospital;
import gestao.respositories.hospital.HospitalRepository;
import gestao.utils.Geolocalizacao.Ponto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HospitalService {

    private final HospitalRepository repository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.repository = hospitalRepository;
    }

    public Page<Hospital> findAll(Pageable page) {
        return this.repository.findAll(page);
    }

    public Hospital create(Hospital hospital) {
        hospital.setBancoDeSangue(BancoDeSangueFactory.createDefault());
        hospital.setLeitos(LeitoFactory.createDefault());
        return this.repository.save(hospital);
    }

    public Optional<Hospital> find(Long id) {
        return this.repository.findById(id);
    }

    public boolean update(Long id, Hospital hospital) {
        return this.repository.findById(id).map(x -> {
            x.setNome(hospital.getNome());
            x.setCep(hospital.getCep());
            x.setLogradouro(hospital.getLogradouro());
            x.setComplemento(hospital.getComplemento());
            x.setBairro(hospital.getBairro());
            x.setLocalidade(hospital.getLocalidade());
            x.setUf(hospital.getUf());
            x.setNumero(hospital.getNumero());
            x.setLatitude(hospital.getLatitude());
            x.setLongitude(hospital.getLongitude());
            x.setFormatted_address(hospital.getFormatted_address());
            x.setBancoDeSangue(hospital.getBancoDeSangue());
            x.setLeitos(hospital.getLeitos());
            this.repository.save(x);
            return true;
        }).orElseThrow(() -> new HospitalNotFoundException());
    }

    public boolean delete(Long id) {
        if (! this.repository.findById(id).isPresent()) {
            throw new HospitalNotFoundException();
        }
        this.repository.deleteById(id);
        return true;
    }

    public List<Hospital> procurarPorHospitaisProximos(Ponto geocolocalizacao) {
        return this.repository.buscarMaisProximosPorGeo(geocolocalizacao);
    }
}
