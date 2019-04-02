package gestao.Hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hospital")
public class HospitalController {
        @Autowired
        HospitalService hospitalService;


        @GetMapping
        public ResponseEntity<List<Hospital>> index() {
            List<Hospital> hospitais = hospitalService.findAll();
            return ResponseEntity.ok().body(hospitais);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Hospital> show(@PathVariable(value = "id") Long id) {
            Optional<Hospital> hospital = this.hospitalService.find(id);
            if (! hospital.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(hospital.get());
        }

        @PostMapping
        public ResponseEntity<String> store(@RequestBody Hospital hospital, BindingResult resultado) {
             if (resultado.hasErrors()) {
                 return ResponseEntity.badRequest().body(null);
             }
             this.hospitalService.create(hospital);
             return ResponseEntity.ok().body("Hospital atualizado");

        }


        @PutMapping("/{id}")
        public ResponseEntity<String> update(@PathVariable(value = "id") Long id, Hospital hospital) {
            if (this.hospitalService.update(id, hospital)) {
                return ResponseEntity.ok().body("Hospital atualizado");
            }
            return ResponseEntity.badRequest().body(null);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String>  delete(@PathVariable(value = "id") Long id) {
            this.hospitalService.delete(id);
            return ResponseEntity.ok().body("Hospital deletado");
        }
}

