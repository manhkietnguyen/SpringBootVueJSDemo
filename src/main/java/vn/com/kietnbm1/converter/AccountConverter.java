package vn.com.kietnbm1.converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import vn.com.kietnbm1.dto.AccountDTO;
import vn.com.kietnbm1.entity.Account;

@Component
public class AccountConverter {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public Account toEntity(AccountDTO dto) {
        Account account = new Account();
        account.setUsername(dto.getUsername());
        account.setEncryptedPassword(
                bCryptPasswordEncoder.encode(dto.getPassword()));
        account.setFirstName(dto.getFirstName());
        account.setLastName(dto.getLastName());
        account.setCompanyName(dto.getCompanyName());
        account.setCountry(dto.getCountry());
        account.setLanguage(dto.getLanguage());
        if (dto.getActive().equals("Yes")) {
            account.setActive(true);
        } else if (dto.getActive().equals("No")){
            account.setActive(false);
        }
        return account;
    }

    public AccountDTO toDTO(Account account) {
        if (account != null) {
            AccountDTO dto = new AccountDTO();
            dto.setId(account.getId());
            dto.setUsername(account.getUsername());
            dto.setFirstName(account.getFirstName());
            dto.setLastName(account.getLastName());
            dto.setCompanyName(account.getCompanyName());
            dto.setCountry(account.getCountry());
            dto.setLanguage(account.getLanguage());
            if (account.isActive()) {
                dto.setActive("Yes");
            } else {
                dto.setActive("No");
            }
            dto.setRole(account.getRole().getName());
            return dto;
        } else {
            return null;
        }
    }
}
