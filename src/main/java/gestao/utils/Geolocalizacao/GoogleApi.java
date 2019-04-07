package gestao.utils.Geolocalizacao;

import org.springframework.web.client.RestTemplate;

public class GoogleApi implements InterfaceGeolocalizacaoAPI {

    private static final String URL_PATTERN = "";

    @Override
    public Ponto buscarPontoPorEndereco(Endereco endereco) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(URL_PATTERN, endereco);
        return restTemplate.getForObject(url, Ponto.class);
    }
}
