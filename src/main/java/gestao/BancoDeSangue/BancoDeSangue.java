package gestao.BancoDeSangue;

import gestao.Hospital.Hospital;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BancoDeSangue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BancoDeSangueENUM tipo;
    private Integer quantidadeEmLitros;

    private Hospital hospital;

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

    public Long getId() {
        return id;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
