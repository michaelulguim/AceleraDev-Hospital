package gestao.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import gestao.produto.Produto;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
