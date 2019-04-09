package gestao.models.leito;

import gestao.models.leito.TipoLeitoENUM;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeitoFactory {
    public static Map<TipoLeitoENUM, Integer> createDefault() {
        Map<TipoLeitoENUM, Integer> leito = new HashMap<>();
        Arrays.stream(TipoLeitoENUM.values()).forEach(x -> leito.put(x, 0));
        return leito;
    }
}
