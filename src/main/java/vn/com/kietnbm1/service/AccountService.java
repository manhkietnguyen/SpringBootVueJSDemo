package vn.com.kietnbm1.service;

import java.util.List;

import vn.com.kietnbm1.dto.AccountDTO;

public interface AccountService {

    List<AccountDTO> findAll();

    AccountDTO save(AccountDTO accountDTO);

    AccountDTO findOneById(long id);

    void deleteById(long id);

    List<AccountDTO> findByUsername(String username);
    
    AccountDTO findOneByUsername(String username);
}
