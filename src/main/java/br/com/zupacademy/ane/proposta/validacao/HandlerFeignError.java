package br.com.zupacademy.ane.proposta.validacao;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class HandlerFeignError implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        if(response.status() == 400){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível realizar a requisição.");
        }else if(response.status() == 404){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não foi encontrado!");
        }else if(response.status() == 422){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "O processo já foi realizado!");
        }else if(response.status() >= 500 && response.status() <= 599){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Servidor indisponível!");
        }
        return new Exception(response.reason());
    }
}
