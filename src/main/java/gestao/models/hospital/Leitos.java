package gestao.models.hospital;

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
