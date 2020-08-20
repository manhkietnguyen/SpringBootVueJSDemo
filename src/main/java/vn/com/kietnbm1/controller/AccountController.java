package vn.com.kietnbm1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.com.kietnbm1.dto.AccountDTO;
import vn.com.kietnbm1.service.AccountService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/accounts")
    public List<AccountDTO> getAllAccounts(
            @RequestParam(value = "username", required = false) String username) {
        if (username == null) {
            return accountService.findAll();
        } else {
            return accountService.findByUsername(username);
        }
    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountDTO> createAccount(
            @RequestBody AccountDTO accountDTO) {
        try {
            if (accountService
                    .findOneByUsername(accountDTO.getUsername()) != null) {
                accountDTO.setErrorMessage("This Account has been existed!");
                return new ResponseEntity<>(accountDTO, HttpStatus.OK);
            } else if (accountDTO.getPassword()
                    .equals(accountDTO.getConfirmPassword()) == false) {
                accountDTO.setErrorMessage(
                        "Password and Confirm Password do not match!");
                return new ResponseEntity<>(accountDTO, HttpStatus.OK);
            } else {
                AccountDTO createdAccount = accountService.save(accountDTO);
                createdAccount
                        .setSuccessMessage("Create Account Successfully!");
                return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable("id") long id,
            @RequestBody AccountDTO accountDTO) {
        AccountDTO accountData = accountService.findOneById(id);
        if (accountData != null) {
            accountData.setFirstName(accountDTO.getFirstName());
            accountData.setLastName(accountDTO.getLastName());
            accountData.setCompanyName(accountDTO.getCompanyName());
            accountData.setCountry(accountDTO.getCountry());
            accountData.setLanguage(accountDTO.getLanguage());
            accountData.setRole(accountDTO.getRole());
            accountData.setActive(accountDTO.getActive());
            return new ResponseEntity<>(accountService.save(accountData),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(
            @PathVariable("id") long id) {
        try {
            AccountDTO accountData = accountService.findOneById(id);
            if (accountData != null) {
                accountService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountDTO> getAccountById(
            @PathVariable("id") long id) {
        AccountDTO accountData = accountService.findOneById(id);
        if (accountData != null) {
            return new ResponseEntity<>(accountData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
