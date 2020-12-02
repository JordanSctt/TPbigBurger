package fr.greta.java.user.domain;

import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.user.persistence.UserRepository;

import java.util.List;

public class UserService {


    private UserWrapper wrapper = new UserWrapper();
    private UserRepository repository = new UserRepository();


    public User findByNameAndPassword(String name, String password) throws ServiceException {
        try {
            User userModel = wrapper.fromEntity(repository.findByNameAndPassword(name, password));
            return userModel;

        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public User createUser(User user) throws ServiceException {
        if(user.nameIsValid() && user.passwordIsValid() && user.phoneIsValid()) {
            try {
                return wrapper.fromEntity(repository.create(wrapper.toEntity(user)));
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
        throw new ServiceException("Le name et le password ne peuvent pas etre vide.");
    }
}
