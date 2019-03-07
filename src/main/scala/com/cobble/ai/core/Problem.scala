package com.cobble.ai.core

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.reflect.ClassTag

/**
  * Defines a problem to be solved.
  *
  * @param c An implicit ClassTag[N] called so we can create arrays in a generic context.
  * @tparam S The Type of the state for this problem. Must be as subtype of [[com.cobble.ai.core.State]].
  * @tparam N The Type of the node for this problem. Must be a type or subtype of [[com.cobble.ai.core.Node]].
  */
abstract class Problem[S <: State[S], N <: Node[_, S]](implicit c: ClassTag[N]) {

    /**
      * The initial node of the problem.
      */
    val initialNode: N

    /**
      * Simply wraps findSolution() in a Future
      * @return A Future that returns Some(Node) if a solution can be found. None otherwise.
      */
    def findSolutionAsync(): Future[Option[N]] = Future(findSolution())

    /**
      * Finds a solution to the problem.
      *
      * @return Returns Some(Node) if a solution can be found. None otherwise.
      */
    def findSolution(): Option[N] = {
        val closedSet: ArrayBuffer[N] = ArrayBuffer[N]()
        var openSet: Array[N] = Array[N](initialNode)
        while (openSet.nonEmpty) {
            val current: N = openSet.head // Set current to the first item in the open set
            if (current.isGoalNode) // If the current node is the goal node then return it.
                return Some(current)
            openSet = openSet.tail // Remove the first item from the open set
            closedSet += current // Add the current node to the closed set
            openSet = (openSet ++
                current.getSuccessors.map(_.asInstanceOf[N]) // Get all possible successors of the node
                    .filterNot(closedSet.contains) //Remove all nodes that are found in the closed set
                ).sortBy(_.f) // Sort the final open set by the evaluation function.
        }
        None
    }

    /**
      * Gets a node's path to the root node.
      *
      * @param node The optional node to get the path for.
      * @return An array of nodes that from the root node to node. If node is None the array will be empty.
      */
    def getPath(node: Option[N]): Array[N] = {
        var currentNode: Option[N] = node
        val path: ArrayBuffer[N] = ArrayBuffer[N]()
        while (currentNode.isDefined) {
            path += currentNode.get
            currentNode = currentNode.get.parent.map(_.asInstanceOf[N])
        }
        path.reverse.toArray
    }

}
