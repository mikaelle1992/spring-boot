package org.example;


import org.example.domain.entity.Cliente;
import org.example.domain.entity.Pedido;
import org.example.domain.repository.Clientes;
import org.example.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class VendaApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes, @Autowired Pedidos pedidos) {
        return args -> {
            System.out.println("Salvando cliente ");

            clientes.save(new Cliente("Mikaelle R."));
            clientes.save(new Cliente("Andre"));
            Cliente pedro =new Cliente("Pedro");
            clientes.save(pedro);

           Pedido p =new Pedido();
           p.setCliente(pedro);
          p.setDataPedido(LocalDate.now());
           // p.setTotal(BigDecimal.valueOf(100));

           pedidos.save(p);

           pedidos.findByCliente(pedro).forEach(System.out::println);
           // Cliente cliente = clientes.findClienteFetchPedidos(pedro.getId());
           // System.out.println(cliente);
           // System.out.println(cliente.getPedido());

           // List<Cliente> result =clientes.encontrarPorNome("Andre");
           // result.forEach(System.out::println);

          // List<Cliente> todosClientes = clientes.findAll();
          // todosClientes.forEach(System.out::println);


        };
    }



    public  static void main(String [] args){

        SpringApplication.run(VendaApplication.class, args);
    }
}
