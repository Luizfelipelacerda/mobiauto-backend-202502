package com.luizlacerda.mobiauto_backend_202502.service;

import com.luizlacerda.mobiauto_backend_202502.Enum.Cargo;
import com.luizlacerda.mobiauto_backend_202502.entity.User;
import com.luizlacerda.mobiauto_backend_202502.entity.UsuarioOportunidade;
import com.luizlacerda.mobiauto_backend_202502.exception.RevendaNotFoundException;
import com.luizlacerda.mobiauto_backend_202502.repository.UserRepository;
import com.luizlacerda.mobiauto_backend_202502.repository.UsuarioOportunidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EscolhaDeUsuarioInteligenteImpl implements EscolhaDeUsuarioInteligente {


    private final  UsuarioOportunidadeRepository usuarioOportunidadeRepository;
    private final UserRepository userRepository;
    
    @Override
    public User escolhaDeUsuario(UUID revendaId){

        Optional<List<UsuarioOportunidade>> allOportunidades = this.usuarioOportunidadeRepository.findAllByRevenda_Id(revendaId);

        if(allOportunidades.get().isEmpty()){
            Optional<List<User>> allUsers = this.userRepository.findAllByRevenda_IdAndCargo(revendaId, Cargo.ASSISTENTE);
            return allUsers.get().stream().findFirst().get();
        }
        List<UsuarioOportunidade> usuarioOportunidades = allOportunidades.get();
        OptionalInt minOp = usuarioOportunidades.stream().mapToInt(one -> one.getOportunidadesEmAndamento()).min();
        Integer min2 = minOp.getAsInt();

        List<LocalDateTime> ultimasOportunidades = usuarioOportunidades.stream().map(u -> u.getUltimaOportunidade()).toList();
        Optional<LocalDateTime> minOp2 = ultimasOportunidades.stream().min(LocalDateTime::compareTo);

        List<UsuarioOportunidade> OportunidadeEscolhida = usuarioOportunidades.stream().filter(n ->
            min2.equals(n.getOportunidadesEmAndamento()) || minOp2.get().equals(n.getUltimaOportunidade())
        ).toList();

        return OportunidadeEscolhida.stream().findFirst().get().getAssistente();

    }
}
