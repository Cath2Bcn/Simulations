package com.example.demo.service;

import com.example.demo.model.Payment;
import com.example.demo.model.Simulation;
import com.example.demo.model.Subscription;
import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.SimulationRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    SimulationService simulationService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    SubscriptionService subscriptionService;


    public void populate() {

        // locale in english
        Faker faker = new Faker(new Locale("en-GB"));

        List<Simulation> simulations;
        List<Payment> payments;
        List<Subscription> subscriptions;
        //Date date = new Date();

        // ref variable creation UUID
        String uniqueID;

        for (int i = 0; i <10 ; i++ ){

            uniqueID = UUID.randomUUID().toString();
            Player player =  new Player();
            player.setId(uniqueID);
            player.setActive(true);
            player.setPlayer( faker.artist().name());
            player.setAge(faker.number().numberBetween(10, 100));

            subscriptions = subscriptionService.createFakeSubscriptions();
            simulations = simulationService.createFakeSimulations();
          
            // add simulations to EACH player
            for (int j = 0; j <10 ; j++ ) {
                player.addSimulation(simulations.get(j));
                player.addSubscription(subscriptions.get(j));

            }

            // add payments to EACH player
            payments = paymentService.createFakePayments();
            for (int j = 0; j <10 ; j++ ) {
                player.addPayment(payments.get(j));
            }

            // eventually we SAVE data (PLAYER + ...) to DB H2 by JPA
            playerRepository.save(player);

        }
    }
}
