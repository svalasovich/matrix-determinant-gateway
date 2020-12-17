package com.matrix.gateway.config

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.lambda.AWSLambda
import com.amazonaws.services.lambda.AWSLambdaClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AWSConfig {

    @Bean
    fun lambdaClient(): AWSLambda = AWSLambdaClientBuilder.standard()
        .withCredentials(EnvironmentVariableCredentialsProvider())
        .withRegion(Regions.EU_WEST_2).build()
}