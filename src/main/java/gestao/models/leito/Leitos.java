package gestao.models.leito;

import gestao.models.leito.TipoLeitoENUM;

public class Leitos {

    private TipoLeitoENUM tipo;

    private Integer quantidade;

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
