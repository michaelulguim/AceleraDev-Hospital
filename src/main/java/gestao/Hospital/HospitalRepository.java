package gestao.Hospital;

import gestao.BancoDeSangue.BancoDeSangue;
import gestao.BancoDeSangue.BancoDeSangueENUM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface HospitalRepository extends JpaRepository<Hospital, Long>, HospitalGeoRepositoryCustom {
//    List<Hospital> findByBancoDeSangue(BancoDeSangueENUM sangue);
//    List<Hospital> findByProdutoNome(String produtoNome);
}