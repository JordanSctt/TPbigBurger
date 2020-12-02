package fr.greta.java.burger.domain;

import fr.greta.java.burger.persistence.BurgerEntity;
import fr.greta.java.burger.persistence.BurgerRepository;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;

import java.util.List;

public class BurgerService {

    private BurgerRepository repository = new BurgerRepository();

    private BurgerWrapper wrapper = new BurgerWrapper();

    public Burger create(Burger burger) throws ServiceException {
        if(burger.isValid()) {
            try {
                Burger burgerCreer = wrapper.fromEntity(repository.create(wrapper.toEntity(burger)));
                return burgerCreer;

            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
        throw new ServiceException("Le label du burger ne peut être vide.");
    }

    public List<Burger> findAllWithBurger() throws ServiceException {
        try {
            List<Burger> models = wrapper.fromEntities(repository.findAll());

            return models;
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void delete(int id) throws ServiceException {

            try {
               repository.delete(id);
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }

        throw new ServiceException("Le label du burger ne peut être vide.");
    }


    public Burger findById(int id) throws ServiceException {
        try {
            return wrapper.fromEntity(repository.findById(id));
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }



}
