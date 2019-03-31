package gestao;

import gestao.Hospital.Hospital;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;

public class mainn {
    public static void main(String[] args) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        String [] myArray = {"location"};

       String url = "https://maps.googleapis.com/maps/api/geocode/json?address=502+Rua+Sao+Paulo+Bela+Vista+Rio+das+Ostras+RJ&key=AIzaSyBfxfY86Slxa5zjLKqmFBTxhwahMkUke6M";

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        String array = responseEntity.getBody().toString();
        System.out.println(array);
       // System.out.println(responseEntity.getBody());
    }
}
