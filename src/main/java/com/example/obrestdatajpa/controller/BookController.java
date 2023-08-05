package com.example.obrestdatajpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;

@RestController
public class BookController {
    private BookRepository bookRepository;

    
    //CRUD sobre la entidad book

    //Buscar todos los libros(lista de libros)
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/api/books")
    public List<Book> findAll(){
        return bookRepository.findAll();

    }

    //buscar 1 libro segun id
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id){
        Optional<Book> bookOpt = bookRepository.findById(id);
        if(bookOpt.isPresent())
            return ResponseEntity.ok(bookOpt.get());
        else
            return ResponseEntity.notFound().build();
    }

    //Crear libro... Obtener cabeceras?? para que?? por seguridad
    @PostMapping("/api/books")
    public Book create(@RequestBody Book book){
        // Guardar el libro recibido por parametros en la base de datos
        return bookRepository.save(book);

    }

    //
    

    
}
