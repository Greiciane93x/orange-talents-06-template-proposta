package br.com.zupacademy.ane.proposta;

import br.com.zupacademy.ane.proposta.integracao.AssociaCartaoPropostaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableFeignClients
@EnableAsync
public class PropostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaApplication.class, args);
	}
}
