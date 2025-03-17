package com.luizlacerda.mobiauto_backend_202502.controller.mapper;

import com.luizlacerda.mobiauto_backend_202502.controller.dto.CreateUserDTO;
import com.luizlacerda.mobiauto_backend_202502.controller.dto.UserReturnDTO;
import com.luizlacerda.mobiauto_backend_202502.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserMapper {

    public User toEntity(CreateUserDTO createUserDTO){
        User user = new User();
        user.setUserName(createUserDTO.nome());
        user.setEmail(createUserDTO.email());
        user.setCargo(createUserDTO.cargo());
        user.setPassword(createUserDTO.senha());
        return user;
    }

    public UserReturnDTO toDTO(User user){
        UserReturnDTO returnDTO =
                new UserReturnDTO(user.getUserName(), user.getEmail(), user.getCargo(),user.getRevenda().getId());
        return returnDTO;
    }

    public List<User> toEntity(List<CreateUserDTO> createUserDTOList){
        return createUserDTOList.stream().map(this::toEntity).toList();
    }

    public List<UserReturnDTO> toDTO(List<User> userList){
        return userList.stream().map(this::toDTO).toList();
    }


}
