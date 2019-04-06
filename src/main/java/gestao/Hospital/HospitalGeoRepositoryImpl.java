package gestao.Hospital;

import gestao.utils.Geolocalizacao.Ponto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class HospitalGeoRepositoryImpl implements HospitalGeoRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Hospital> buscarMaisProximosPorGeo(Ponto point) {
        String sql = "SELECT hospital.*, " +
                "(6371 * acos(" +
                "cos( radians( :lat ) ) " +
                "* cos( radians( latitude ) ) " +
                "* cos( radians( longitude ) - radians( :lon ) ) " +
                "+ sin( radians( :lat ) ) " +
                "* sin( radians( latitude ) ) ) ) AS distancia " +
                " FROM hospital ORDER BY distancia ASC LIMIT 5";

        Query query = entityManager.createNativeQuery(sql, Hospital.class);
        query.setParameter("lon", point.getLongitude());
        query.setParameter("lat", point.getLatitude());

        return query.getResultList();

    }
}
