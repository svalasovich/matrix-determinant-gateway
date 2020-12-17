package com.matrix.gateway.service

interface MatrixService {

    fun getDeterminant(size: Int): Pair<Array<IntArray>, Int>
}