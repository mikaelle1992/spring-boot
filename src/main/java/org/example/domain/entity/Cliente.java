package org.example.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name=" Cliente")

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name="id")
    private Integer id;

    @Column(name="nome",length=100)
    private String nome;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido>pedidos;

    public Cliente(){

    }
    public Set<Pedido> getPedido() {
        return pedidos;
    }

    public void setPedido(Set<Pedido> pedido) {
        this.pedidos = pedido;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cliente(String nome){
        this.nome=nome;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String  toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
