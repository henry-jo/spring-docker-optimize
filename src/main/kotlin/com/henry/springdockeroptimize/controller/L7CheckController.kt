package com.henry.springdockeroptimize.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class L7CheckController {

    @GetMapping("/l7check")
    fun l7check() = ResponseEntity.ok("OK")
} 