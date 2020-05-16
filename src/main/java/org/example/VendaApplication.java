package org.example;


import org.example.domain.entity.Cliente;
import org.example.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class VendaApplication {

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired Clientes clientes){
        return  args -> {
            Cliente c =new Cliente( null,"Roberto");
            clientes.save(c);
        };
    }


    public  static void main(String [] args){

        SpringApplication.run(VendaApplication.class, args);
    }
}
