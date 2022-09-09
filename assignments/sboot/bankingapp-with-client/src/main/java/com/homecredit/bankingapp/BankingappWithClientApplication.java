package com.homecredit.bankingapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BankingappWithClientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BankingappWithClientApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception{

        RestTemplate restTemplate= new RestTemplate();

        Account account= new Account();
        account.setBranch("BOB");
        account.setName("Mohit");
        account.setType("loan");
        account.setBalance(48674);

        Account account1= new Account();
        account.setAccountId(4);
        account.setBranch("Axis");
        account.setName("Mohit");
        account.setType("saving");
        account.setBalance(8900);

        String baseUrl = "http://localhost:8080/accounts";
        String getAccount = "http://localhost:8080/accounts/24";

        // Get All Account
        Object allFetchAccount = restTemplate.getForObject(baseUrl, Object.class);
        System.out.println(allFetchAccount);


        // Get Account By Id
        Object fetchAccount = restTemplate.getForObject(getAccount,Object.class);
        System.out.println(fetchAccount);


         // Create Account
         String createAccount = restTemplate.postForObject(baseUrl,account,String.class);
         System.out.println(createAccount);

        // Update Account
        ResponseEntity<String> response1 = restTemplate.exchange("http://localhost:8080/accounts",
                        HttpMethod.PUT,new HttpEntity<>(account), String.class, account1);
        System.out.println(response1);

        // Delete Account
        ResponseEntity<String> response2 = restTemplate.exchange("http://localhost:8080/accounts/4",
                        HttpMethod.DELETE, new HttpEntity<>(account), String.class, account.getAccountId());
        System.out.println(response2);

    }
}

