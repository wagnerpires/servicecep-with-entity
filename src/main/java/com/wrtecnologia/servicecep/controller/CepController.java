package com.wrtecnologia.servicecep.controller;

import com.wrtecnologia.servicecep.dto.CepDTO;
import com.wrtecnologia.servicecep.service.CepService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("/endereco")
public class CepController {

    private CepService cepService;
    private CepDTO cepDTO;

    @GetMapping("/{cep}")
    public ResponseEntity<CepDTO> buscaEnderecoPeloCep(@PathVariable("cep") String cep) throws Exception {

        cepDTO = cepService.buscaEnderecoPeloCep(cep);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cep}").buildAndExpand(cepDTO.getCep()).toUri();
        return ResponseEntity.created(uri).body(cepDTO);
    }
}
