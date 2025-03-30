package com.bank_api.service.impl;

import com.bank_api.domain.model.Account;
import com.bank_api.domain.model.Card;
import com.bank_api.domain.model.User;
import com.bank_api.domain.repository.UserRepository;
import com.bank_api.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    @Override
    public User create(User userToCreate) {
        // Verificar se a conta já existe
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalArgumentException("This account already exists");
        }

        // Criar objetos completamente novos
        User newUser = new User();
        newUser.setName(userToCreate.getName());

        Account account = new Account();
        account.setNumber(userToCreate.getAccount().getNumber());
        account.setAgency(userToCreate.getAccount().getAgency());
        account.setBalance(userToCreate.getAccount().getBalance());
        account.setLimit(userToCreate.getAccount().getLimit());
        newUser.setAccount(account);

        Card card = new Card();
        card.setNumber(userToCreate.getCard().getNumber());
        card.setLimit(userToCreate.getCard().getLimit());
        newUser.setCard(card);

        return userRepository.save(newUser);
    }
}
