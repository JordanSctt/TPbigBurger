package fr.greta.java.burger.facade;

import fr.greta.java.burger.domain.Produit;
import fr.greta.java.burger.domain.ProduitType;

import java.util.ArrayList;
import java.util.List;

public class ProduitDTOWrapper {

    public List<ProduitDTO> toDTOS(List<Produit> models) {
        List<ProduitDTO> dtos = new ArrayList<>();
        for(Produit model : models) {
            dtos.add(toDTO(model));
        }
        return dtos;
    }


    public ProduitDTO toDTO(Produit model) {
        ProduitDTO dto = new ProduitDTO();
        dto.setId(model.getId());
        dto.setLabel(model.getLabel());
        dto.setPrice(model.getPrice());
        dto.setProduitType(ProduitType.valueOf(model.getProduitType().name()));
        return dto;
    }

}
