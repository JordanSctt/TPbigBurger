package fr.greta.java.cuisto.domain;

import fr.greta.java.cuisto.persistence.CuistoRepository;
import fr.greta.java.generic.exception.RepositoryException;

import java.util.ArrayList;
import java.util.List;

public class CuistoService {

    CuistoRepository repository = new CuistoRepository();
    CuistoWrapper wrapper = new CuistoWrapper();

    public List<Cuisto> findAllCuisto() throws RepositoryException {

        List<Cuisto> cuistoList = wrapper.fromEntities(repository.findAllCuisto());

      return cuistoList;
    }


}
