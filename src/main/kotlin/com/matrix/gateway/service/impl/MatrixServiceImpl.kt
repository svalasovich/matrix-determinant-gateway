package com.matrix.gateway.service.impl

import com.amazonaws.services.lambda.AWSLambda
import com.amazonaws.services.lambda.model.InvokeRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.matrix.gateway.service.MatrixService
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class MatrixServiceImpl(
    private val objectMapper: ObjectMapper,
    private val lambdaClient: AWSLambda
) : MatrixService {

    override fun getDeterminant(size: Int): Pair<Array<IntArray>, Int> {
        if (size < 2) {
            throw NumberFormatException("Please enter other size")
        }

        val response = lambdaClient.invoke(
            InvokeRequest()
                .withFunctionName("matrix-determinant")
                .withPayload("$size")
                .withSdkRequestTimeout(60000)
        )
        return objectMapper.readValue(String(response.payload.array(), StandardCharsets.UTF_8))
    }
}