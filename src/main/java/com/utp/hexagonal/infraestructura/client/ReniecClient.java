package com.utp.hexagonal.infraestructura.client;

import com.utp.hexagonal.infraestructura.dto.ReniecResponseDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class ReniecClient {

    private static final String RENIEC_URL = "https://api.consultasperu.com/api/v1/query";
    private static final String TOKEN = "0d8a421f690efe566a62783f57a4f0ad73253f964334b2bd20a6587af11aeadf"; // reemplazar con token real

    public ReniecResponseDTO consultarPorDni(String dni) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("token", TOKEN);
        body.put("type_document", "dni");
        body.put("document_number", dni);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(RENIEC_URL, request, Map.class);

            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && Boolean.TRUE.equals(responseBody.get("success"))) {
                Map<String, String> data = (Map<String, String>) responseBody.get("data");

                ReniecResponseDTO dto = new ReniecResponseDTO();
                dto.setDni(data.get("number"));
                dto.setNombres(data.get("name"));
                dto.setApellidos(data.get("surname")); // 👈
                dto.setFechaNacimiento(data.get("date_of_birth"));
                dto.setSexo(data.get("sexo")); // Asegúrate que la API devuelve este campo
                return dto;
            }
        } catch (Exception e) {
            System.out.println("Error al consultar RENIEC: " + e.getMessage());
        }
        return null;
    }

}