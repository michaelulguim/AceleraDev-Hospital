package gestao.Hospital;

import gestao.BancoDeSangue.BancoDeSangueENUM;

import gestao.Leito.Leitos;

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


    @NotBlank
    private String cep;


    @NotBlank
    private String logradouro;


    private String complemento;


    @NotBlank
    private String bairro;


    @NotBlank
    private String localidade;


    @NotBlank
    private String uf;

    @NotBlank
    private String numero;

    private String formatted_address;

    @Min(value = -90, message = "A latitude deve estar contida no intervalo [-90, 90]")
    @Max(value = +90, message = "A latitude deve estar contida no intervalo [-90, 90]")
    @NotNull(message = "A latitude não deve ser nula e deve ser um número real.")
    private Double latitude;

    @Min(value = -180, message = "A longitude deve estar contida no intervalo [-180, 180]")
    @Max(value = +180, message = "A longitude deve estar contida no intervalo [-180, 180]")
    @NotNull(message = "A longitude não deve ser nula e deve ser um número real.")
    private Double longitude;

    @ElementCollection
    private Map<BancoDeSangueENUM, Integer> bancoDeSangue;


    @OneToMany(targetEntity=Leitos.class, cascade=CascadeType.ALL, mappedBy = "hospital")
    private List<Leitos> leitos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNumero() {
        return numero;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }


    public Map<BancoDeSangueENUM, Integer> getBancoDeSangue() {
        return bancoDeSangue;
    }

    public void setBancoDeSangue(Map<BancoDeSangueENUM, Integer> bancoDeSangue) {
        this.bancoDeSangue = bancoDeSangue;
    }
    public List<Leitos> getLeitos() {
        return leitos;
    }

    public void setLeitos(List<Leitos> leitos) {
        this.leitos = leitos;
    }
}
