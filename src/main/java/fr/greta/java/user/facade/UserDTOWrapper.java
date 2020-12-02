package fr.greta.java.user.facade;

import fr.greta.java.user.domain.Admin;
import fr.greta.java.user.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTOWrapper {

    public List<UserDTO> toDTOS(List<User> models) {
        List<UserDTO> dtos = new ArrayList<>();
        for(User model : models) {
            dtos.add(toDTO(model));
        }
        return dtos;
    }

    public UserDTO toDTO(User model) {

        if (model.isAdmin()) {

            AdminDTO dto = new AdminDTO();
            dto.setId(model.getId());
            dto.setName(model.getName());
            dto.setPassword(model.getPassword());
            dto.setPhone(model.getPhone());
            return dto;
        } else {
            UserDTO dto = new UserDTO();
            dto.setId(model.getId());
            dto.setName(model.getName());
            dto.setPassword(model.getPassword());
            dto.setPhone(model.getPhone());
            return dto;
        }
    }
}
