package com.example.weddingCard.service;

import com.example.weddingCard.dto.AccountsDTO;
import com.example.weddingCard.dto.SideDTO;
import com.example.weddingCard.entity.Accounts;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.enums.Relation;
import com.example.weddingCard.enums.Side;
import com.example.weddingCard.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountsService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Transactional
    public void saveAccounts(SideDTO sideDTO, Information information, Side side) {
        String relationship = sideDTO.getRelationship();
        if(sideDTO.getMe() != null) {
            accountsRepository.save(dtoAccountsEntity(sideDTO.getMe(), information, Relation.ME, side, relationship));
        }
        if(sideDTO.getFather() != null) {
            accountsRepository.save(dtoAccountsEntity(sideDTO.getFather(), information, Relation.FATHER, side, relationship));
        }
        if(sideDTO.getMother() != null) {
            accountsRepository.save(dtoAccountsEntity(sideDTO.getMother(), information, Relation.MOTHER, side, relationship));
        }
    }

    private Accounts dtoAccountsEntity(AccountsDTO accountsDTO, Information information, Relation relation, Side side, String relationship) {
        Optional<Accounts> findByWeddingRelationSideAccounts = accountsRepository.findByWeddingIdAndRelationAndSide(information, relation, side);
        Accounts accounts;
        if (findByWeddingRelationSideAccounts.isEmpty()) {
            accounts = new Accounts();
        } else {
            accounts = findByWeddingRelationSideAccounts.get();
        }
        accounts.setWeddingId(information);
        accounts.setSide(side);
        accounts.setRelation(relation);
        accounts.setRelationship(relationship);
        accounts.setName(accountsDTO.getName());
        accounts.setBank(accountsDTO.getBank());
        accounts.setAccount(accountsDTO.getAccount());
        accounts.setContact(accountsDTO.getContact());
        return accounts;
    }
}
