package gestao.exceptions;

public class HospitalNotFoundException extends RuntimeException {
    public HospitalNotFoundException() {
        super("Hospital não encontrado");
    }
}
