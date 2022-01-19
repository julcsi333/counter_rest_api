package com.example.counter

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CounterController {
    var counter: Long = 0L

    @GetMapping("/count")
    fun getCount(): ResponseEntity<Counter> {
        return ResponseEntity<Counter>(
            Counter(counter),
            HttpStatus.OK
        )
    }

    @PostMapping("/count/add")
    fun add(@RequestBody(required = true) counterToAdd: Counter): ResponseEntity<Any> {
        counter += counterToAdd.value
        return ResponseEntity.ok()
            .build()
    }

    @PostMapping("/count/subtract")
    fun subtract(@RequestBody(required = true) counterToSubtract: Counter): ResponseEntity<Any> {
        counter -= counterToSubtract.value
        return ResponseEntity.ok()
            .build()
    }
}