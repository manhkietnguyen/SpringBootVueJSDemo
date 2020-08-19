package vn.com.kietnbm1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.kietnbm1.converter.AccountConverter;
import vn.com.kietnbm1.dto.AccountDTO;
import vn.com.kietnbm1.entity.Account;
import vn.com.kietnbm1.entity.Role;
import vn.com.kietnbm1.repository.AccountRepository;
import vn.com.kietnbm1.repository.RoleRepository;
import vn.com.kietnbm1.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountConverter accountConverter;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<AccountDTO> findAll() {
        List<AccountDTO> accountDTOs = new ArrayList<>();
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            AccountDTO dto = new AccountDTO();
            dto = accountConverter.toDTO(account);
            accountDTOs.add(dto);
        }
        return accountDTOs;
    }

    @Override
    public AccountDTO save(AccountDTO accountDTO) {
        if (accountDTO.getId() == 0) {
            Role role = roleRepository.findByName(accountDTO.getRole());
            Account account = accountConverter.toEntity(accountDTO);
            if (role != null) {
                account.setRole(role);
                Account accountSaved = accountRepository.save(account);
                return accountConverter.toDTO(accountSaved);
            } else {
                role = new Role();
                role.setName(accountDTO.getRole());
                Role roleSaved = roleRepository.save(role);
                account.setRole(roleSaved);
                Account accountSaved = accountRepository.save(account);
                return accountConverter.toDTO(accountSaved);
            }
        } else {
            Account account = accountRepository.findOneById(accountDTO.getId());
            account.setFirstName(accountDTO.getFirstName());
            account.setLastName(accountDTO.getLastName());
            account.setCompanyName(accountDTO.getCompanyName());
            account.setCountry(accountDTO.getCountry());
            account.setLanguage(accountDTO.getLanguage());
            Role role = roleRepository.findByName(accountDTO.getRole());
            account.setRole(role);
            return accountConverter.toDTO(accountRepository.save(account));
        }

    }

    @Override
    public AccountDTO findOneById(long id) {
        Account account = accountRepository.findOneById(id);
        if (account != null) {
            return accountConverter.toDTO(account);
        }
        return null;
    }

    @Override
    public void deleteById(long id) {
        Account account = accountRepository.findOneById(id);
        if (account != null) {
            accountRepository.deleteById(id);
        }
    }

    @Override
    public AccountDTO findOneByUsername(String username) {
        return accountConverter
                .toDTO(accountRepository.findOneByUsername(username));
    }
}