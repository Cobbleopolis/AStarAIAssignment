package com.cobble.ai

import com.cobble.ai.eightPuzzle.{EightPuzzleAction, EightPuzzleNode, EightPuzzleState}

object Main {

    val INITIAL_STATE: EightPuzzleState = EightPuzzleState(
        2, 8, 3,
        1, 6, 4,
        7, 0, 5
    )

    val GOAL_STATE: EightPuzzleState = EightPuzzleState(
        1, 2, 3,
        8, 0, 4,
        7, 6, 5
    )

    def main(args: Array[String]): Unit = {
        val initialNode: EightPuzzleNode = EightPuzzleNode(INITIAL_STATE, GOAL_STATE)
        println("Hello, World!")
        println(initialNode)
        println("Index: " + INITIAL_STATE.zeroIndex)
        println("Location: " + INITIAL_STATE.zeroLocation)
        val secondState: EightPuzzleState = INITIAL_STATE.applyAction(EightPuzzleAction.MOVE_UP).get.asInstanceOf[EightPuzzleState]
        println(secondState)
        println("Index: " + secondState.zeroIndex)
        println("Location: " + secondState.zeroLocation)
    }

}
