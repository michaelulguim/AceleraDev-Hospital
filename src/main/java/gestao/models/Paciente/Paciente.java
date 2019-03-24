package gestao.models.Paciente;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Paciente {



    @Id
    private Long cpf;
    private String nome;
    private LocalDate dataNascimento;
    private char sexo;
    private LocalDateTime ultimoCheckin;
    private boolean emAtendimento;

    private Long hospital;





    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Long getHospital() {
        return hospital;
    }

    public void setHospital(Long hospital) {
        this.hospital = hospital;
    }

    public LocalDateTime getUltimoCheckin() {
        return ultimoCheckin;
    }

    public void setUltimoCheckin(LocalDateTime ultimoCheckin) {
        this.ultimoCheckin = ultimoCheckin;
    }

    public boolean isEmAtendimento() {
        return emAtendimento;
    }

    public void setEmAtendimento(boolean emAtendimento) {
        this.emAtendimento = emAtendimento;
    }
}
