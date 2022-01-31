package com.castro.sbpay;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

//vai servir como webservice
//dadosCartao vai ser em formato json
@RestController
public class SBPayService {

    private static final String AUTH_TOKEN="r2d2";

//    ResponseEntity - é uma entidade de resposta

    @PostMapping(path = "/pay",consumes=MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentResponse> pagar(
            @RequestHeader("Token") String token,
            @Valid @RequestBody DadosCartao dadosCartao,
            Errors errors
    ){
        //fazendo a checagem de segurança
        if(!AUTH_TOKEN.equals(token)){
            return ResponseEntity.badRequest().body(new PaymentResponse("Token inválido"));
        }

        if(errors.hasErrors()){
            return ResponseEntity.ok(new PaymentResponse(StatusPagamento.CartaoInvalido));
        }

        String numCartao=dadosCartao.getNumCartao();

        StatusPagamento statusPagamento = numCartao.startsWith("1111")?StatusPagamento.Autorizado:StatusPagamento.NaoAutorizado;

        return ResponseEntity.ok(new PaymentResponse(statusPagamento));

    }
}
