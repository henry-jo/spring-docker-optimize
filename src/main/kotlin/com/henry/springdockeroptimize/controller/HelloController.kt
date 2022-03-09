package com.henry.springdockeroptimize.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/test")
    fun test() = ResponseEntity.ok("HELLO!")
}
