package com.example.handsonmsakotlin

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("customers")
class CustomerController(private val customerService: CustomerService) {
    @GetMapping("{id}")
    fun getOne(@PathVariable id : Long) : ResponseEntity<Mono<Customer>> =
        ResponseEntity.ok(customerService.findById(id))
    @GetMapping
    fun getAll(@RequestParam name : String) : ResponseEntity<Flux<Customer>> =
        ResponseEntity.ok(customerService.findAll(name))
}