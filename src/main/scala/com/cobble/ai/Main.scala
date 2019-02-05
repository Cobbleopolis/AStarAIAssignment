package com.cobble.ai

import com.cobble.ai.eightPuzzle.{EightPuzzleNode, EightPuzzleState}

import scala.collection.mutable.ArrayBuffer

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

    val closedSet: ArrayBuffer[EightPuzzleNode] = ArrayBuffer[EightPuzzleNode]()
    var openSet: Array[EightPuzzleNode] = Array[EightPuzzleNode](EightPuzzleNode(INITIAL_STATE, GOAL_STATE))

    def findSolution(): Option[EightPuzzleNode] = {
        while (openSet.nonEmpty) {
            val current: EightPuzzleNode = openSet.head
            if (current.isGoalNode)
                return Some(current)
            openSet = openSet.tail
            closedSet += current
            openSet = (openSet ++ current.getSuccessors.map(_.asInstanceOf[EightPuzzleNode]).filterNot(closedSet.contains)).sortBy(_.f)
        }
        None
    }

    def main(args: Array[String]): Unit = {
        println("Initial State:")
        println(INITIAL_STATE.toPrettyString)
        println("Goal State:")
        println(GOAL_STATE.toPrettyString + "\n")

        val startTime: Long = System.currentTimeMillis()
        val foundNode: Option[EightPuzzleNode] = findSolution()
        val searchTime: Long = System.currentTimeMillis() - startTime

        var currentNode: Option[EightPuzzleNode] = foundNode
        val path: ArrayBuffer[EightPuzzleState] = ArrayBuffer[EightPuzzleState]()
        while (currentNode.isDefined) {
            path += currentNode.get.state
            currentNode = currentNode.get.parent.asInstanceOf[Option[EightPuzzleNode]]
        }
        println("Solution Path:")
        path.reverse.map(_.toPrettyString + "\n").foreach(println)

        println(s"Found in: $searchTime ms")
    }

}
