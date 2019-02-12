package com.cobble.ai

import com.cobble.ai.eightPuzzle.{EightPuzzleNode, EightPuzzleProblem, EightPuzzleState}

import scala.collection.mutable.ArrayBuffer

object Main {

    val eightPuzzleProblem: EightPuzzleProblem = new EightPuzzleProblem

    def main(args: Array[String]): Unit = {
        println("Initial State:")
        println(eightPuzzleProblem.INITIAL_STATE.toPrettyString)
        println("Goal State:")
        println(eightPuzzleProblem.GOAL_STATE.toPrettyString + "\n")

        val startTime: Long = System.currentTimeMillis()
        val foundNode: Option[EightPuzzleNode] = eightPuzzleProblem.findSolution()
        val searchTime: Long = System.currentTimeMillis() - startTime

        var currentNode: Option[EightPuzzleNode] = foundNode
        val path: ArrayBuffer[EightPuzzleState] = ArrayBuffer[EightPuzzleState]()
        while (currentNode.isDefined) {
            path += currentNode.get.state
            currentNode = currentNode.get.parent.asInstanceOf[Option[EightPuzzleNode]]
        }
        println("Solution Path:")
        if (path.nonEmpty)
            path.reverse.map(_.toPrettyString + "\n").foreach(println)
        else
            println("No path found!")

        println(s"Found in: $searchTime ms")
    }

}
