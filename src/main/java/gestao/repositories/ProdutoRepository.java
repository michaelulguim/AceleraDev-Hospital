package gestao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import gestao.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
