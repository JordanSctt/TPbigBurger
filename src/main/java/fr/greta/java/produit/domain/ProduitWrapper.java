package fr.greta.java.produit.domain;


import fr.greta.java.produit.persistence.ProduitEntity;

import java.util.ArrayList;
import java.util.List;

public class ProduitWrapper {

        public List<Produit> fromEntities(List<ProduitEntity> entities) {

            List<Produit> models = new ArrayList<>();
            for (ProduitEntity entity : entities) {
                models.add(fromEntity(entity));
            }
            return models;
        }

    public Produit fromEntity(ProduitEntity entity) {
        Produit model = new Produit();
        model.setId(entity.getId());
        model.setLabel(entity.getLabel());
        model.setPrice(entity.getPrice());
        model.setProduitType(entity.getProduitType());
        return model;
    }


    public ProduitEntity toEntity(Produit model) {
        ProduitEntity entity = new ProduitEntity();
        entity.setId(model.getId());
        entity.setLabel(model.getLabel());
        entity.setPrice(model.getPrice());
        entity.setProduitType(model.getProduitType());
        return entity;
    }





    }

