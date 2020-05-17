package org.example.rest.controller;


import org.example.domain.entity.Cliente;
import org.example.domain.entity.Produto;
import org.example.domain.repository.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
 private Produtos produtosCont;

 //construtor
    public ProdutoController(Produtos produtosCont) {
        this.produtosCont = produtosCont;
    }
   @GetMapping("{id}")
    public Produto getprodutoById(@PathVariable  Integer id){
        return produtosCont.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"produto nao " +
                "encontrado"));
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public Produto save(@RequestBody Produto produto){
        return produtosCont.save(produto);
   }
     @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
     public void delete (@PathVariable Integer id){
        produtosCont.findById(id).map(produto -> {
            produtosCont.delete(produto);
            return produto;
        }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"PRODUTO NAO ENCONTRADO"));
     }
     @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void update(@PathVariable Integer id,@RequestBody Produto produto){
        produtosCont.findById(id).map(produtoExistente->{
            produto.setId(produtoExistente.getId());
            produtosCont.save(produto);
            return produto;
        }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"PRODUTO NAO ENCONTRADO"));
     }

    @GetMapping
    public List<Cliente> find (Produto filtro){
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher
                (ExampleMatcher.StringMatcher.CONTAINING);
        Example example= Example.of(filtro,matcher);
        return produtosCont.findAll(example);

    }
}
