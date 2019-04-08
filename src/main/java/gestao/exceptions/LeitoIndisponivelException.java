package gestao.exceptions;

public class LeitoIndisponivelException extends RuntimeException {
    public LeitoIndisponivelException() {
        super("Nenhum leito deste tipo está disponível no momento");
    }
}
