package gestao.utils;

import gestao.utils.Geolocalizacao.Endereco;
import org.springframework.web.client.RestTemplate;

public class Cep {
    private static final String URL_PATTERN = "https://viacep.com.br/ws/%s/json";

    public static Object consultar(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(URL_PATTERN, cep);
        return restTemplate.getForObject(URL_PATTERN, Endereco.class);
    }
}
