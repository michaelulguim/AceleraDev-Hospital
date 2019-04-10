package gestao.utils.Geolocalizacao;

import gestao.models.Endereco;

public interface InterfaceGeolocalizacaoAPI {
    public Ponto buscarPontoPorEndereco(Endereco endereco);
}
