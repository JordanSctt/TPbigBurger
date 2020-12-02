package fr.greta.java.user.domain;

import fr.greta.java.user.persistence.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserWrapper {

    public List<User> fromEntities(List<UserEntity> entities) {
        List<User> models = new ArrayList<>();
        for (UserEntity entity : entities) {
            models.add(fromEntity(entity));
        }
        return models;
    }

    public UserEntity toEntity(User model) {
        UserEntity entity = new UserEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setPassword(model.getPassword());
        entity.setPhone(model.getPhone());
        return entity;
    }

    public User fromEntity(UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setPassword(entity.getPassword());
        model.setPhone(entity.getPhone());
        return model;
    }

}
