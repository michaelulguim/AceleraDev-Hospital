package gestao.Hospital;

import gestao.BancoDeSangue.BancoDeSangueENUM;
import gestao.utils.Geolocalizacao.Ponto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hospitais")
@Api(value="API REST Hospitais")
@CrossOrigin(origins = "*")
public class HospitalController {
        @Autowired
        HospitalService hospitalService;


        @GetMapping
        @ApiOperation(value="Retorna uma lista de Hospitais")
        public ResponseEntity<List<Hospital>> index() {
            List<Hospital> hospitais = hospitalService.findAll();
            return ResponseEntity.ok().body(hospitais);
        }

        @GetMapping("/{id}")
        @ApiOperation(value="Retorna um Hospital")
        public ResponseEntity<Hospital> show(@PathVariable(value = "id") Long id) {
            Optional<Hospital> hospital = this.hospitalService.find(id);
            if (! hospital.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(hospital.get());
        }

        @PostMapping
        @ApiOperation(value="Salva um hospital.")
        public ResponseEntity<Hospital> store(@RequestBody Hospital hospital, BindingResult resultado) {
             if (resultado.hasErrors()) {
                 return ResponseEntity.badRequest().body(null);
             }
             this.hospitalService.create(hospital);
             return ResponseEntity.ok().body(hospital);

        }

        @PutMapping("/{id}")
        @ApiOperation(value="Atualiza Hospital")
        public ResponseEntity<String> update(@PathVariable(value = "id") Long id, @RequestBody Hospital hospital) {
            if (this.hospitalService.update(id, hospital)) {
                return ResponseEntity.ok().body("Hospital atualizado");
            }
            return ResponseEntity.badRequest().body(null);
        }

        @DeleteMapping("/{id}")
        @ApiOperation(value="Remove um hospita.")
        public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
            this.hospitalService.delete(id);
            return ResponseEntity.ok().body("Hospital deletado");
        }


        @GetMapping(value = "/encaminhamento")
        public ResponseEntity<List<Hospital>> findNearHospital(@Valid Ponto geocolocalizacao) {
            List<Hospital> hospitais = hospitalService.procurarPorHospitaisProximos(geocolocalizacao);
            return ResponseEntity.ok().body(hospitais);
        }


}

