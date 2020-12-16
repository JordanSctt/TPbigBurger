package fr.greta.java.livreur.facade;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.livreur.domain.Livreur;
import fr.greta.java.livreur.persistence.LivreurEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LivreurDTOWrapper {


    public List<LivreurDTO> toDTOS(List<Livreur> models) throws RepositoryException {
        List<LivreurDTO> dtos = new ArrayList<>();
        for(Livreur model : models) {
            dtos.add(toDTO(model));
        }
        return dtos;
    }

    public LivreurDTO toDTO(Livreur model) throws RepositoryException {
        LivreurDTO dto = new LivreurDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setPresence(model.getPresence().name());
        if(model.getCommandeEnCours() != null) {
            dto.setCommandeEnCours(model.getCommandeEnCours().getId());
        }
        return dto;
    }


}
