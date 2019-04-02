package gestao.Leito;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Leito {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    private String tipo;

    @NotNull
    private Integer quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
