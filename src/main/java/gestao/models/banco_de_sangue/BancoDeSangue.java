package gestao.models.banco_de_sangue;

import gestao.models.banco_de_sangue.BancoDeSangueENUM;

public class BancoDeSangue {

    private BancoDeSangueENUM tipo;
    private Integer quantidadeEmLitros;

    public BancoDeSangueENUM getTipo() {
        return tipo;
    }

    public void setTipo(BancoDeSangueENUM tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidadeEmLitros() {
        return quantidadeEmLitros;
    }

    public void setQuantidadeEmLitros(Integer quantidadeEmLitros) {
        this.quantidadeEmLitros = quantidadeEmLitros;
    }
}
