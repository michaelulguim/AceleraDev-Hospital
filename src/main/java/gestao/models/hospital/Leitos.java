package gestao.models.hospital;

import gestao.models.TipoLeito;

public class Leitos {

    private TipoLeito tipo;

    private Integer quantidade;

    public TipoLeito getTipo() {
        return tipo;
    }

    public void setTipo(TipoLeito tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
