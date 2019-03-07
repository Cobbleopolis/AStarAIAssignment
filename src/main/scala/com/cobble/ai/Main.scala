package com.cobble.ai

import com.cobble.ai.eightPuzzle.{EightPuzzleAction, EightPuzzleNode, EightPuzzleProblem}
import com.cobble.ai.nqueen.{NQueenNode, NQueenProblem}

import scala.concurrent.{Await, TimeoutException}
import scala.concurrent.duration._

object Main {

    val PRINT_STEPS: Boolean = true

    val MAX_SEARCH_TIME: Duration = 1.hour

    def main(args: Array[String]): Unit = {
        println("Eight Puzzle:")
        eightPuzzle()
        println("\nN Queen:")
        nQueen()
    }

    def eightPuzzle(): Unit = {
        val eightPuzzleProblem: EightPuzzleProblem = new EightPuzzleProblem
        println("Initial State:")
        println(eightPuzzleProblem.INITIAL_STATE.toPrettyString)
        println("Goal State:")
        println(eightPuzzleProblem.GOAL_STATE.toPrettyString + "\n")
        var foundNode: Option[EightPuzzleNode] = None
        val startTime: Long = System.currentTimeMillis()
        try {
            foundNode = Await.result(eightPuzzleProblem.findSolutionAsync(), MAX_SEARCH_TIME)
        } catch {
            case _: TimeoutException =>
                println("No solution found withing the max search time")
                return
        }
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
                path.map(_.state.toPrettyString + "\n").foreach(println)
        else
            println("No path found!")

        println(s"Found in: $searchTime ms")
    }

    def nQueen(): Unit = {
        val nQueenProblem: NQueenProblem = new NQueenProblem
        var foundNode: Option[NQueenNode] = None
        val startTime: Long = System.currentTimeMillis()
        try {
            foundNode = Await.result(nQueenProblem.findSolutionAsync(), MAX_SEARCH_TIME)
        } catch {
            case _: TimeoutException =>
                println("No solution found withing the max search time")
                return
        }
        val searchTime: Long = System.currentTimeMillis() - startTime
        val path: Array[NQueenNode] = nQueenProblem.getPath(foundNode)
        if (path.nonEmpty)
            path.map(_.state.toPrettyString + "\n").foreach(println)
        else
            println("No path found!")

        println(s"Found in: $searchTime ms")
    }

}
