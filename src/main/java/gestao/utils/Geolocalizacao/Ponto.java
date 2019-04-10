package gestao.utils.Geolocalizacao;

import javax.validation.constraints.NotNull;

public class Ponto {
    @NotNull
    private final Double longitude;
    @NotNull
    private final Double latitude;


    public Ponto(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }


    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }
}
