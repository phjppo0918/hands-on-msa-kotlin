package com.example.handsonmsakotlin

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap

@Service
class CustomerServiceImpl : CustomerService {
    companion object {
        val customers: List<Customer> = listOf(
            Customer(1, "a"),
            Customer(2, "aa"),
            Customer(3, "b"),
            Customer(4, "bb")
        )
    }

    val storage: MutableMap<Long, Customer> = ConcurrentHashMap(customers.associateBy(Customer::id));
    override fun save(customer: Customer) {
        val nextId: Long = storage.size + 1.toLong()
        storage[nextId] = customer;
    }

    override fun findById(id: Long): Mono<Customer> = storage[id].toMono()

    override fun findAll(nameFilter: String): Flux<Customer> =
        storage.filter {
                it.value.name.contains(nameFilter, true)
            }.map(Map.Entry<Long, Customer>::value)
            .toFlux()
}