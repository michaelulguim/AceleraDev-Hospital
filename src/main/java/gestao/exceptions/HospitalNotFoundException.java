package gestao.exceptions;

import javax.persistence.EntityNotFoundException;

public class HospitalNotFoundException extends EntityNotFoundException {
    public HospitalNotFoundException() {
        super("Hospital não encontrado");
    }
}
