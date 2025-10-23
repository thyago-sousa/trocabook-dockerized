package com.trocabook.Trocabook.service;

import com.trocabook.Trocabook.controllers.response.ChatResponse;
import com.trocabook.Trocabook.model.dto.MensagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Service
public class ChatService implements IChatService {
    private final String url; // <-- NOVA LINHA

    private RestTemplate restTemplate;

    @Autowired
    public ChatService(RestTemplate restTemplate,
                       @Value("${chat.api.base.url}") String url) { // <-- MUDANÇA AQUI
        this.restTemplate = restTemplate;
        this.url = url; // <-- MUDANÇA AQUI
    }

    @Override
    public ChatResponse<MensagemDTO> enviarMensagem(MensagemDTO mensagemDTO) {
        HttpEntity<MensagemDTO> entidade = new HttpEntity<>(mensagemDTO);
        ResponseEntity<ChatResponse<MensagemDTO>> response = restTemplate.exchange(
                url + "/mensagens",
                HttpMethod.POST,
                entidade,
                new ParameterizedTypeReference<ChatResponse<MensagemDTO>>(){}
        );
        return response.getBody();
    }

    @Override
    public ChatResponse<List<MensagemDTO>> listarMensagensEntreUsuarios(int cdUsuarioLivro, int cdUsuarioDestinatario, int cdUsuarioRemetente) {
        String endpoint = String.format("%s/mensagens?remetente=%d&destinatario=%d&usuarioLivro=%d", url, cdUsuarioRemetente, cdUsuarioDestinatario, cdUsuarioLivro);
        ResponseEntity<ChatResponse<List<MensagemDTO>>> response = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ChatResponse<List<MensagemDTO>>>() {
                }

        );
        return response.getBody();
    }

    @Override
    public ChatResponse<List<MensagemDTO>> listarMensagensPorUsuarioDataEnvioDecrescente(int cdUsuarioRemetente) {
        String endpoint = String.format("%s/mensagens?remetente=%d", url, cdUsuarioRemetente);
        ResponseEntity<ChatResponse<List<MensagemDTO>>> response = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ChatResponse<List<MensagemDTO>>>() {}
        );
        return response.getBody();
    }
}
