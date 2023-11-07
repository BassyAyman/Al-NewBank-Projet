package fr.igorbanque.retrieverservice.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CardDto {
    private String creditCartNumber;
    private String creditCartDateExpiration;
    private String cvv;
}
