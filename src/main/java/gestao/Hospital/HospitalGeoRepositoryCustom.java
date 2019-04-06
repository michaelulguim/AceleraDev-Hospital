package gestao.Hospital;

import gestao.utils.Geolocalizacao.Ponto;

import java.util.List;

public interface HospitalGeoRepositoryCustom {
     public List<Hospital> buscarMaisProximosPorGeo(Ponto point);
}
