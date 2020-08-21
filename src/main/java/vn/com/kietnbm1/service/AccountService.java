package vn.com.kietnbm1.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import vn.com.kietnbm1.dto.AccountDTO;

public interface AccountService {

    List<AccountDTO> findAll();

    AccountDTO save(AccountDTO accountDTO);

    AccountDTO findOneById(long id);

    void delete(AccountDTO accountDTO);

    List<AccountDTO> findByUsername(String username);

    AccountDTO findOneByUsername(String username);

    List<AccountDTO> findAll(Pageable pageable);

    int totalItem();

    List<AccountDTO> findByUsernameContaining(String username,
            Pageable pageable);

    int totalItemByUsername(String username);
}
