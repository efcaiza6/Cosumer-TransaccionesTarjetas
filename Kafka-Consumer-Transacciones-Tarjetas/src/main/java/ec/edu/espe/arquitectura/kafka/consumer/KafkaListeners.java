/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.espe.arquitectura.generador.dto.TransaccionRQ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Windows Boo
 */
@Slf4j
@Component
public class KafkaListeners {

    private final RestTemplate restTemplate = new RestTemplate();

    @KafkaListener(
            topics = "lbyl3ivs-tarjetas",
            groupId = "lbyl3ivs-tarjetas"
    )
    public void Listener(TransaccionRQ transaccionRQ) {
        log.info("Data recibida sin procesar nada: {}", transaccionRQ);
        //this.restTemplate.postForObject("http://localhost:8090/api/v1/", transaccionRQ, TransaccionRQ.class);
    }
}
