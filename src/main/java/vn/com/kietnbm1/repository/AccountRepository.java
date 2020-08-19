package vn.com.kietnbm1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.com.kietnbm1.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findOneByUsername(String username);

    Account findOneById(long id);
}
