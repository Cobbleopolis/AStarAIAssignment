package com.cobble.ai

import com.cobble.ai.eightPuzzle.{EightPuzzleNode, EightPuzzleState}

import scala.collection.mutable.ArrayBuffer

object Main {

//    val INITIAL_STATE: EightPuzzleState = EightPuzzleState(Array(
//        2, 8, 3,
//        1, 6, 4,
//        7, 0, 5
//    ), 3, 3)
//
//    val GOAL_STATE: EightPuzzleState = EightPuzzleState(Array(
//        1, 2, 3,
//        8, 0, 4,
//        7, 6, 5
//    ), 3, 3)

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

        if (!INITIAL_STATE.isValid) {
            System.err.println("Initial State not valid!")
            System.err.println(INITIAL_STATE.toPrettyString)
            System.exit(-1)
        }
        if (!GOAL_STATE.isValid) {
            System.err.println("Goal State not valid!")
            System.err.println(GOAL_STATE.toPrettyString)
            System.exit(-1)
        }

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
        if (path.nonEmpty)
            path.reverse.map(_.toPrettyString + "\n").foreach(println)
        else
            println("No path found!")

        println(s"Found in: $searchTime ms")
    }

}
