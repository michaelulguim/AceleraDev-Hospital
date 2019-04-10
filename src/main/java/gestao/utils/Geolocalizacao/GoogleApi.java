package gestao.utils.Geolocalizacao;

import gestao.models.Endereco;
import org.springframework.web.client.RestTemplate;

public class GoogleApi implements InterfaceGeolocalizacaoAPI {

    private final String URL_PATTERN = "https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s";
    private final String TOKEN = "AIzaSyDKbiuvRBa1McusIOiXtx9hp6de-9q6xIA";

    @Override
    public Ponto buscarPontoPorEndereco(Endereco endereco) {
        String formattedAddressToAPI = endereco.formattedAddress().replace(" ", "+");
        String url = String.format(URL_PATTERN, formattedAddressToAPI, TOKEN);
        RestTemplate restTemplate = new RestTemplate();
        return  restTemplate.getForObject(url, Ponto.class);
    }

}
