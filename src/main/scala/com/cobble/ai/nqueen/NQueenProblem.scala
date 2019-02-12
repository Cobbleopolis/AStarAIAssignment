package com.cobble.ai.nqueen

import com.cobble.ai.core.Problem

import scala.collection.mutable.ArrayBuffer

class NQueenProblem extends Problem[NQueenState, NQueenNode] {

    val BOARD_SIZE: Int = 8

    val QUEEN_COUNT: Int = 8

    val INITIAL_STATE: NQueenState = NQueenState(Array.fill[Byte](BOARD_SIZE * BOARD_SIZE)(0))

    override val initialNode: NQueenNode = NQueenNode(INITIAL_STATE, QUEEN_COUNT)
}
