package com.wrtecnologia.servicecep.service;

import com.wrtecnologia.servicecep.util.CepUtil;
import com.wrtecnologia.servicecep.dto.CepDTO;
import com.wrtecnologia.servicecep.domain.Cep;
import com.google.gson.Gson;
import com.wrtecnologia.servicecep.mapper.CepDTOMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class CepService {
    private static final String webService = "http://viacep.com.br/ws/";

    private CepDTO cepDTO;

    public static <CepDTO> CepDTO buscaEnderecoPeloCep(String cep) throws Exception {

        String urlParaChamada = webService + cep + "/json";

        try {
            URL url = new URL(urlParaChamada);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            if (conexao.getResponseCode() != 200)
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());

            BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
            String jsonEmString = CepUtil.converteJsonEmString(resposta);

            Gson gson = new Gson();
            Cep endereco = gson.fromJson(jsonEmString, Cep.class);

            return (CepDTO) CepDTOMapper.parseParaDTO(endereco);
            //return parseParaDTO(endereco);

        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }
}