package gestao.Hospital;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.fasterxml.jackson.databind.node.JsonNodeType.POJO;

@RestController
@RequestMapping("/api/v1/hospital")
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
        @ApiOperation(value="Salva um Hospitais")
        public ResponseEntity<Hospital> store(@RequestBody Hospital hospital, BindingResult resultado) {
             if (resultado.hasErrors()) {
                 return ResponseEntity.badRequest().body(null);
             }
             this.hospitalService.create(hospital);
             return ResponseEntity.ok().body(hospital);

        }


        @PutMapping("/{id}")
        @ApiOperation(value="Atualiza Hospitais")
        public ResponseEntity<String> update(@PathVariable(value = "id") Long id, Hospital hospital) {
            if (this.hospitalService.update(id, hospital)) {
                return ResponseEntity.ok().body("Hospital atualizado");
            }
            return ResponseEntity.badRequest().body(null);
        }

        @DeleteMapping("/{id}")
        @ApiOperation(value="Remove Hospitais")
        public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
            this.hospitalService.delete(id);
            return ResponseEntity.ok().body("Hospital deletado");
        }
}

