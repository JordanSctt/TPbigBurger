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

    public User fromDTO(UserDTO dto) {

        if (dto.isAdmin()) {

            Admin model = new Admin();
            model.setId(dto.getId());
            model.setName(dto.getName());
            model.setPassword(dto.getPassword());
            model.setPhone(dto.getPhone());
            model.setRole(dto.getRole());
            model.setAdresse(dto.getAdresse());
            return model;

        } else {
            User model = new User();
            model.setId(dto.getId());
            model.setName(dto.getName());
            model.setPassword(dto.getPassword());
            model.setPhone(dto.getPhone());
            model.setRole(dto.getRole());
            model.setAdresse(model.getAdresse());
            return model;
        }
    }
    public UserDTO toDTO(User model) {

        if (model.isAdmin()) {

            AdminDTO dto = new AdminDTO();
            dto.setId(model.getId());
            dto.setName(model.getName());
            dto.setPassword(model.getPassword());
            dto.setPhone(model.getPhone());
            dto.setRole(model.getRole());
            dto.setAdresse(model.getAdresse());
            return dto;
        } else {
            UserDTO dto = new UserDTO();
            dto.setId(model.getId());
            dto.setName(model.getName());
            dto.setPassword(model.getPassword());
            dto.setPhone(model.getPhone());
            dto.setRole(model.getRole());
            dto.setAdresse(model.getAdresse());
            return dto;
        }
    }
}
