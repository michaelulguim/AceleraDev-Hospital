package gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import gestao.models.Produto;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
