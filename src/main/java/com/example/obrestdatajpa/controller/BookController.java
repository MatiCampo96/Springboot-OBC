  package com.example.obrestdatajpa.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);
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
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        // Guardar el libro recibido por parametros en la base de datos
        if(book.getId() != null){ //quiere decir que existe el id y por lo tanto no es una creacion
            log.warn("Tryng to create a book with id");
            System.out.println("Tryng to create a book with id");
            return ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);

    }

    /**
     * actualizar el libro existente en base de datos
     */
    @PutMapping("/api/books")
    public Book update(@RequestBody Book book){

        return bookRepository.save(book);
    }

    //TODO! Me quede en el minuto 33:47 del video
    

    
}
