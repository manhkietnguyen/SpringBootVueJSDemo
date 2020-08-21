package vn.com.kietnbm1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.com.kietnbm1.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByUsernameContaining(String username);

    Account findOneById(long id);

    Account findOneByUsername(String username);

    Page<Account> findByUsernameContaining(String username, Pageable pageable);
}
