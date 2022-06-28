package com.entelgy.retotecnico;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RetoController {

    // Aggregate root
    // tag::get-aggregate-root[]

    @Autowired
    private RetoService retoService;

    @Value("${mi.direccion}")
    private String direccion;

    @GetMapping("/reto")
    String reto_get() {
        return "se aplico get exitosamente";
    }

    @PostMapping("/reto")
    String reto_post(@RequestBody String requestString) {
        String requestStringOut = "";
        try {
            JSONObject validacion = retoService.adapter_string_a_json(requestString);
            if (validacion.getString("accion").equals("si")) {
                requestStringOut = retoService.facade(direccion);
            } else {
                requestStringOut = "la accion solicitada no es la correcta";
            }

        } catch (Exception e) {
            requestStringOut = "no es un formato json";
        }
        return requestStringOut;
    }
}