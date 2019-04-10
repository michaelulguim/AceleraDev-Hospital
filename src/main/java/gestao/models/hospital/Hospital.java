package gestao.models.hospital;


import gestao.models.Produto.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import gestao.models.Endereco;

import gestao.models.banco_de_sangue.BancoDeSangueENUM;
import gestao.models.leito.TipoLeitoENUM;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@Entity
public class Hospital {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank
    private String nome;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private Endereco endereco;

    @ElementCollection
    private Map<BancoDeSangueENUM, Integer> bancoDeSangue;

    @ElementCollection
    private Map<TipoLeitoENUM, Integer> leitos;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Produto> produtos;

    Hospital() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Map<BancoDeSangueENUM, Integer> getBancoDeSangue() {
        return bancoDeSangue;
    }

    public void setBancoDeSangue(Map<BancoDeSangueENUM, Integer> bancoDeSangue) {
        this.bancoDeSangue = bancoDeSangue;
    }

    public Map<TipoLeitoENUM, Integer> getLeitos() {
        return leitos;
    }

    public void setLeitos(Map<TipoLeitoENUM, Integer> leitos) {
        this.leitos = leitos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public static Hospital criarViaDTO(HospitalDTO dto) {
        Hospital hospital = new Hospital();
        hospital.nome = dto.getNome();
        hospital.endereco = dto.getEndereco();
        hospital.leitos = dto.getLeitos();
        return hospital;
    }

    public void atualizarViaDTO(HospitalDTO dto) {
        this.nome = dto.getNome();
        this.endereco = dto.getEndereco();
        this.leitos = dto.getLeitos();
    }
}
