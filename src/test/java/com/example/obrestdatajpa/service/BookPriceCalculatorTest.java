package com.example.obrestdatajpa.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import com.example.obrestdatajpa.entities.Book;

public class BookPriceCalculatorTest {
    @Test
    void CalculatePriceTest() {

        //Configuracion de la prueba
        Book book = new Book(12L, "LosANgeles", "JKR", 212, 220.0, LocalDate.now(), true);
        BookPriceCalculator calculator = new BookPriceCalculator();
        calculator.calculatePrice(book);

        //Se ejecuta el comportamiento a testear
        double price = calculator.calculatePrice(book);
        System.out.println(price);

        //Comprobaciones aserciones
        assertTrue(price > 0);
        assertEquals(222.99, price);
    }
}
