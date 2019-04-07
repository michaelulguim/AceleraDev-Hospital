package gestao.exceptions.paciente;

public class PacienteNaoEncontradoException extends RuntimeException{

    public PacienteNaoEncontradoException(String msg) {
        super(msg);
    }
}
