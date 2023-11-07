package fr.igorbanque.retrieverservice.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountInfoDto {
    private int amountMoney;
    private int inDebitAmount;
    private int accountLimit;
}
