package gestao.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gestao.utils.Geolocalizacao.Ponto;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O logradouro não pode ser nulo e deve existir")
    private String logradouro;

    private String complemento;

    @NotBlank(message = "Bairro não pode ser nulo e deve existir")
    private String bairro;


    @NotBlank(message = "Localidade não pode ser nula e deve existir")
    private String localidade;


    @NotBlank
    @Size(max = 2, min = 2, message = "a Sigla de UF de ve conter apenas 2 letras")
    private String uf;

    @NotBlank
    private String numero;

    @Range(min = -90, max = 90,  message = "A longitude deve estar contida no intervalo [-90, 90]")
    @NotNull(message = "A latitude não deve ser nula e deve ser um número real.")
    private Double latitude;

    @Range(min = -180, max = 180, message = "A longitude deve estar contida no intervalo [-180, 180]")
    @NotNull(message = "A longitude não deve ser nula e deve ser um número real.")
    private Double longitude;

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

    public void setNumero(String numero) {
        this.numero = numero;
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

    @JsonIgnore
    public void adicionarCoordenadas(Ponto coordenadas) {
        this.setLongitude(coordenadas.getLongitude());
        this.setLatitude(coordenadas.getLatitude());
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }

    public String formattedAddress() {
        return String.format("%s %s, %s, %s, %s", logradouro, numero, bairro, localidade, uf);
    }

}

