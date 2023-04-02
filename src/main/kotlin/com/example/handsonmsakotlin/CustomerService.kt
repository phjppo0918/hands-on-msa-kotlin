package com.example.handsonmsakotlin

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomerService {
    fun save(customer: Customer)
    fun findById(id: Long): Mono<Customer>
    fun findAll(nameFilter: String): Flux<Customer>
}