package gestao.Leito;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Leito {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    private TipoLeitoENUM tipo;

    @NotNull
    private Integer quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoLeitoENUM getTipo() {
        return tipo;
    }

    public void setTipo(TipoLeitoENUM tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
