package gestao.utils.Geolocalizacao;

import javax.validation.constraints.NotNull;

public class Ponto {
    @NotNull
    private final Long longitude;
    @NotNull
    private final Long latitude;


    public Ponto(Long longitude, Long latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }


    public Long getLongitude() {
        return longitude;
    }

    public Long getLatitude() {
        return latitude;
    }
}
