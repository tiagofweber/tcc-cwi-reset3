package io.github.cwireset.tcc.service.usuario;

import io.github.cwireset.tcc.request.UsuarioAvatarRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsuarioAvatarService {

    public UsuarioAvatarRequest buscarAvatar() {
        RestTemplate restTemplate = new RestTemplate();
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplate = restTemplateBuilder.build();

        return restTemplate.
                getForObject(
                        "https://some-random-api.ml/img/dog",
                        UsuarioAvatarRequest.class
                );
    }
}
