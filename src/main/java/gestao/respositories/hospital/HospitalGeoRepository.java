package gestao.respositories.hospital;

import gestao.models.hospital.Hospital;
import gestao.utils.Geolocalizacao.Ponto;

import java.util.List;

public interface HospitalGeoRepository {
     public List<Hospital> buscarMaisProximosPorGeo(Ponto ponto);

}
