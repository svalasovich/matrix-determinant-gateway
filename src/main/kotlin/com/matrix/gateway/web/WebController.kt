package com.matrix.gateway.web

import com.matrix.gateway.service.MatrixService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.time.Duration

@RestController
@RequestMapping("/matrix")
class WebController(private val matrixService: MatrixService) {


    @GetMapping("determinant")
    fun getDeterminant(@RequestParam(defaultValue = "10") size: Int) =
        Mono.just(matrixService.getDeterminant(size))
            .subscribeOn(Schedulers.boundedElastic())
}