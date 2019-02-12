package com.cobble.ai.eightPuzzle

import com.cobble.ai.core.Problem

class EightPuzzleProblem extends Problem[EightPuzzleState, EightPuzzleNode] {

    val INITIAL_STATE: EightPuzzleState = EightPuzzleState(Array(
        2, 8, 3,
        1, 6, 4,
        7, 0, 5
    ))

    val GOAL_STATE: EightPuzzleState = EightPuzzleState(Array(
        1, 2, 3,
        8, 0, 4,
        7, 6, 5
    ))

    if (!INITIAL_STATE.isValid)
        throw new IllegalStateException("Initial State not valid!")

    if (!GOAL_STATE.isValid)
        throw new IllegalStateException("Goal State not valid!")

    //    val INITIAL_STATE: EightPuzzleState = EightPuzzleState(Array(
    //        14, 11,  9,  7,
    //         1,  6, 12, 13,
    //        15,  4, 10,  0,
    //         3,  5,  2,  8
    //    ))

    //    val GOAL_STATE: EightPuzzleState = EightPuzzleState(Array(
    //        0, 1, 2, 3,
    //        4, 5, 6, 7,
    //        8, 9, 10, 11,
    //        12, 13, 14, 15
    //    ))

    //    val INITIAL_STATE: EightPuzzleState = EightPuzzleState(Array(
    //        1, 2, 3, 4,
    //        5, 6, 0, 8,
    //        9, 10, 7, 11,
    //        13, 14, 15, 12
    //    ))
    //
    //    val GOAL_STATE: EightPuzzleState = EightPuzzleState(Array(
    //        1, 2, 3, 4,
    //        5, 6, 7, 8,
    //        9, 10, 11, 12,
    //        13, 14, 15, 0
    //    ))

    override val initialNode: EightPuzzleNode = EightPuzzleNode(INITIAL_STATE, GOAL_STATE)
}
