package com.cobble.ai.nqueen

import com.cobble.ai.core.Problem

import scala.collection.mutable.ArrayBuffer

class NQueenProblem extends Problem[NQueenState, NQueenNode] {

    val BOARD_SIZE: Int = 8

    val QUEEN_COUNT: Int = 8

    val INITIAL_STATE: NQueenState = NQueenState(Array.fill[Byte](BOARD_SIZE * BOARD_SIZE)(0))

    override val initialNode: NQueenNode = NQueenNode(INITIAL_STATE, QUEEN_COUNT)

    /**
      * Gets a node's path to the root node.
      *
      * @param node The optional node to get the path for.
      * @return An array of nodes that from the root node to node. If node is None the array will be empty.
      */
    def getPath(node: Option[NQueenNode]): Array[NQueenNode] = {
        var currentNode: Option[NQueenNode] = node
        val path: ArrayBuffer[NQueenNode] = ArrayBuffer[NQueenNode]()
        while (currentNode.isDefined) {
            path += currentNode.get
            currentNode = currentNode.get.parent
        }
        path.reverse.toArray
    }
}
