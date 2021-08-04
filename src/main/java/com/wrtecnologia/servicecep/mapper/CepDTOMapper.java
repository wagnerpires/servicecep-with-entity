package com.wrtecnologia.servicecep.mapper;

import com.wrtecnologia.servicecep.dto.CepDTO;
import com.wrtecnologia.servicecep.domain.Cep;

import java.io.Serializable;

public class CepDTOMapper {

    public static CepDTO parseParaDTO(Cep cep) {

        return CepDTO.builder()
                .cep(cep.getCep())
                .logradouro(cep.getLogradouro())
                .complemento(cep.getComplemento())
                .bairro(cep.getBairro())
                .localidade(cep.getLocalidade())
                .uf(cep.getUf())
                .build();
    }
}
