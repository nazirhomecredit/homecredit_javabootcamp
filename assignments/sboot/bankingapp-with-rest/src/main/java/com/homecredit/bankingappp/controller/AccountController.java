package com.homecredit.bankingappp.controller;


import com.homecredit.bankingappp.exception.ApplicationException;
import com.homecredit.bankingappp.model.Account;
import com.homecredit.bankingappp.model.ResponseMessage;
import com.homecredit.bankingappp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() throws ApplicationException {
        return ResponseEntity.ok().body(accountService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable int id) throws ApplicationException{
        return ResponseEntity.ok().body(accountService.get(id));
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createAccount(@RequestBody @Valid Account account) throws ApplicationException{
        accountService.create(account);
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus("SUCCESS");
        responseMessage.setMessage("Account Created Successfully");
        return ResponseEntity.ok().body(responseMessage);
    }

    @PutMapping
    public ResponseEntity<ResponseMessage> updateAccount(@RequestBody Account account) throws ApplicationException {
        accountService.update(account);
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus("SUCCESS");
        responseMessage.setMessage("Account Updated Successfully");
        return ResponseEntity.ok().body(responseMessage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteAccount(@PathVariable int id) throws ApplicationException {
        accountService.delete(id);
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus("SUCCESS");
        responseMessage.setMessage("Account Deleted Successfully");
        return ResponseEntity.ok().body(responseMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage> handleValidationError(MethodArgumentNotValidException e){
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus("Failure");
        StringBuilder errorMsg = new StringBuilder();
        for(ObjectError error: e.getAllErrors()) {
            errorMsg.append(error.getDefaultMessage());
            errorMsg.append(" ");

        }
        responseMessage.setMessage("Validation Error: " + errorMsg);
        return ResponseEntity.badRequest().body(responseMessage);
    }

}
