package gestao.Paciente;

public class PacienteNaoEncontradoException extends RuntimeException{

    public PacienteNaoEncontradoException(String msg) {
        super(msg);
    }
}
