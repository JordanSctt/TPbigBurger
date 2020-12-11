package fr.greta.java.livreur.facade;

import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.livreur.domain.Livreur;

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
        dto.setPresence(model.getPresence());
        if (model.getCommande().getId() > 0) {
            dto.setStartDatePrep(model.getCommande().getStartDatePrep().toString());
            dto.setEndDatePrep(model.getCommande().getEndDatePrep().toString());
            dto.setEtatCommande(model.getCommande().getEtatCommande().name());
            dto.setStartDateLivraison(model.getCommande().getStartDateLivraison().toString());
            dto.setEndDateLivraison(model.getCommande().getEndDateLivraison().toString());
        }
        return dto;
    }

    public String formatDate(LocalDateTime localDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formatDateTime = localDate.format(formatter);

        return formatDateTime;
    }
}
