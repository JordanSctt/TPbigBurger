package fr.greta.java.burger.facade;

import fr.greta.java.burger.domain.Burger;

import java.util.ArrayList;
import java.util.List;

public class BurgerDTOWrapper {

    public List<BurgerDTO> toDTOS(List<Burger> models) {
        List<BurgerDTO> dtos = new ArrayList<>();
        for(Burger model : models) {
            dtos.add(toDTO(model));
        }
        return dtos;
    }



    public BurgerDTO toDTO(Burger model) {
        BurgerDTO dto = new BurgerDTO();
        dto.setId(model.getId());
        dto.setLabel(model.getLabel());
        dto.setPrice(model.getPrice());
        return dto;
    }

}
