package fr.greta.java.burger.domain;

import fr.greta.java.burger.persistence.ProduitRepository;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;

import java.util.List;

public class ProduitService {

    private ProduitRepository repository = new ProduitRepository();

    private ProduitWrapper wrapper = new ProduitWrapper();

    public Produit create(Produit produit) throws ServiceException {
        if(produit.isValid()) {
            try {
                Produit burgerCreer = wrapper.fromEntity(repository.create(wrapper.toEntity(produit)));
                return burgerCreer;

            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
        throw new ServiceException("Le label et le prix du produit ne peuvent être vide.");
    }

    public List<Produit> findAllWithProduit() throws ServiceException {
        try {
            List<Produit> models = wrapper.fromEntities(repository.findAll());

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

        throw new ServiceException("Le label du produit ne peut être vide.");
    }


    public Produit findById(int id) throws ServiceException {
        try {
            return wrapper.fromEntity(repository.findById(id));
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }



}
