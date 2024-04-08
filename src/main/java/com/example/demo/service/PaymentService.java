package com.example.demo.service;

import com.example.demo.model.Payment;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class PaymentService {

    public List<Payment> createFakePayments() {

        // locale in english
        Faker faker = new Faker(new Locale("en-GB"));
        Date date = new Date();
        List<Payment> payments = new ArrayList<>();

        // ref variable creation UUID
        String uniqueID;

        for (int i = 0; i <10 ; i++ ){

            uniqueID = UUID.randomUUID().toString();
            Payment payment = new Payment(
                    uniqueID,
                    date.toString(),
                    faker.number().randomDouble(2, 0, 1000),
                    faker.animal().name(),
                    false,
                    null
                    );
            payments.add(payment);

        }

        return payments;
    }

}
