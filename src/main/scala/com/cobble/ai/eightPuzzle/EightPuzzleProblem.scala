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

    /**
      * Gets a node's path to the root node.
      *
      * @param node The node to get the path for.
      * @return An array of nodes that from the root node to node.
      */
    def getPath(node: EightPuzzleNode): Array[EightPuzzleNode] = getPath(Option(node))

    /**
      * Gets a node's path to the root node.
      *
      * @param node The optional node to get the path for.
      * @return An array of nodes that from the root node to node. If node is None the array will be empty.
      */
    def getPath(node: Option[EightPuzzleNode]): Array[EightPuzzleNode] = {
        var currentNode: Option[EightPuzzleNode] = node
        val path: ArrayBuffer[EightPuzzleNode] = ArrayBuffer[EightPuzzleNode]()
        while (currentNode.isDefined) {
            path += currentNode.get
            currentNode = currentNode.get.parent.asInstanceOf[Option[EightPuzzleNode]]
        }
        path.reverse.toArray
    }
}
