package gestao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.TimeZone;
import javax.annotation.PostConstruct;

@SpringBootApplication
public class GestaoHospitalApplication {

    @PostConstruct
    public void fusoHorario() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }

    public static void main(String[] args) {
        SpringApplication.run(GestaoHospitalApplication.class, args);
    }

}
