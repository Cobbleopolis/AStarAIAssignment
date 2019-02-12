package com.cobble.ai

import com.cobble.ai.eightPuzzle.{EightPuzzleAction, EightPuzzleNode, EightPuzzleProblem}

object Main {

    val PRINT_STEPS: Boolean = true

    val eightPuzzleProblem: EightPuzzleProblem = new EightPuzzleProblem

    def main(args: Array[String]): Unit = {
        println("Initial State:")
        println(eightPuzzleProblem.INITIAL_STATE.toPrettyString)
        println("Goal State:")
        println(eightPuzzleProblem.GOAL_STATE.toPrettyString + "\n")

        val startTime: Long = System.currentTimeMillis()
        val foundNode: Option[EightPuzzleNode] = eightPuzzleProblem.findSolution()
        val searchTime: Long = System.currentTimeMillis() - startTime
        val path: Array[EightPuzzleNode] = eightPuzzleProblem.getPath(foundNode)
        println("Solution Path:")
        if (path.nonEmpty)
            if (PRINT_STEPS)
                path.map(_.action match {
                    case Some(EightPuzzleAction.MOVE_UP) => "Move the blank space up"
                    case Some(EightPuzzleAction.MOVE_DOWN) => "Move the blank space down"
                    case Some(EightPuzzleAction.MOVE_LEFT) => "Move the blank space left"
                    case Some(EightPuzzleAction.MOVE_RIGHT) => "Move the blank space right"
                    case None => "Initial State"
                    case _ => "???"
                }).foreach(println)
            else
                path.map(_.state.toPrettyString + "\n").reverse.foreach(println)
        else
            println("No path found!")

        println(s"Found in: $searchTime ms")
    }

}
