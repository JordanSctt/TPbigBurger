package fr.greta.java.burger.domain;


import fr.greta.java.burger.persistence.ProduitEntity;
import fr.greta.java.commande.domain.CommandeEtat;

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
        model.setProduitType(ProduitType.valueOf(String.valueOf(entity.getProduitType())));
        return model;
    }


    public ProduitEntity toEntity(Produit model) {
        ProduitEntity entity = new ProduitEntity();
        entity.setId(model.getId());
        entity.setLabel(model.getLabel());
        entity.setPrice(model.getPrice());
        return entity;
    }





    }

