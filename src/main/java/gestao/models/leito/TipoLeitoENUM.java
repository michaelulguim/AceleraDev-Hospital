package gestao.models.leito;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Tipos de Leitos
 * PREFIXOS [CLI = CLINICO, CIR = CIRURGICO, PED = PEDIATRIA, OBS = OBSTETRICIA]
 * @author michaelulguim
 *
 */

public enum TipoLeitoENUM {

    CIR_BUCOMAXILOFACIAL,
    CIR_CARDIOLOGIA,
    CIR_CIRURGIAGERAL,
    CIR_ENDOCRINOLOGIA,
    CIR_GASTROENTEROLOGIA,
    CIR_GINECOLOGIA,
    CIR_NEFROLOGIA_UROLOGIA,
    CIR_NEUROCIRURGIA,
    CIR_OFTALMOLOGIA,
    CIR_ONCOLOGIA,
    CIR_ORTOPEDIA_TRAUMATOLOGIA,
    CIR_OTORRINOLARINGOLOGIA,
    CIR_PLASTICA,
    CIR_TORAXICA,
    CIR_TRANSPLANTE,
    CIR_QUEIMADOADULTO,
    CIR_QUEIMADOPEDIATRICO,

    CLI_AIDS,
    CLI_CARDIOLOGIA,
    CLI_CLINICAGERAL,
    CLI_DERMATOLOGIA,
    CLI_GERIATRIA,
    CLI_HANSENOLOGIA,
    CLI_HEMATOLOGIA,
    CLI_LEITO_DIA,
    CLI_NEFRO_UROLOGIA,
    CLI_NEONATOLOGIA,
    CLI_NEUROLOGIA,
    CLI_ONCOLOGIA,
    CLI_PNEUMOLOGIA,
    CLI_SAUDEMENTAL,
    CLI_QUEIMADOADULTO,
    CLI_QUEIMADOPEDIÁTRICO,

    OBS_CIRURGICA,
    OBS_CLINICA,
    PED_CIRURGICO,
    PED_PEDIATRICA,

    OUTROS;


   @JsonCreator
     public static TipoLeitoENUM create(String name) {
        return valueOf(name);
    }

    @JsonValue
    public String toJson() {
        return name();
    }
}
