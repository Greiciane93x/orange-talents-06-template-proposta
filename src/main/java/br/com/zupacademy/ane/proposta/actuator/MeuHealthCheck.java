package br.com.zupacademy.ane.proposta.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MeuHealthCheck implements HealthIndicator {
    @Override
    public Health health() {
        Map<String, Object> details = new HashMap<>();
        details.put("versão", "1.2.3");
        details.put("descrição", "Customização do Heath Check");

        return Health.status(Status.UP).withDetails(details).build();
    }
}
