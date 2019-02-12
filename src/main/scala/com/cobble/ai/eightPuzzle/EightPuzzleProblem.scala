package com.cobble.ai.eightPuzzle

import com.cobble.ai.core.Problem

import scala.collection.mutable.ArrayBuffer

class EightPuzzleProblem extends Problem[EightPuzzleState, EightPuzzleNode] {

//    val INITIAL_STATE: EightPuzzleState = EightPuzzleState(Array(
//        2, 8, 3,
//        1, 6, 4,
//        7, 0, 5
//    ))
//
//    val GOAL_STATE: EightPuzzleState = EightPuzzleState(Array(
//        1, 2, 3,
//        8, 0, 4,
//        7, 6, 5
//    ))

    val INITIAL_STATE: EightPuzzleState = EightPuzzleState(Array(
        1, 2, 3, 4,
        5, 6, 0, 8,
        9, 10, 7, 11,
        13, 14, 15, 12
    ))

    val GOAL_STATE: EightPuzzleState = EightPuzzleState(Array(
        1, 2, 3, 4,
        5, 6, 7, 8,
        9, 10, 11, 12,
        13, 14, 15, 0
    ))

    if (!INITIAL_STATE.isValid)
        throw new ExceptionInInitializerError("Initial State not valid!")

    if (!GOAL_STATE.isValid)
        throw new ExceptionInInitializerError("Goal State not valid!")

    override val initialNode: EightPuzzleNode = EightPuzzleNode(INITIAL_STATE, GOAL_STATE)
}
