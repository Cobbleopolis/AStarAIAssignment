package com.cobble.ai.core

/**
  * A node representing a state's location within the search tree.
  *
  * @param state  The state of the problem at this node.
  * @param parent The optional parent of this node. Some if this has a parent. None otherwise.
  * @tparam T The Type of the state being stored.
  */
abstract class Node[T <: State](state: T, parent: Option[Node[T]] = None) {

    /**
      * The evaluation function value of this node.
      */
    lazy val f: Int = g + h // This value is lazy so it is not defined when the parent (this) constructor is called

    /**
      * The cost so far to reach the current node.
      * This will be 0 if the parent node is None.
      */
    val g: Int = parent.map(_.g + 1).getOrElse(0)

    /**
      * The value of the heuristic for the node's current state.
      */
    val h: Int


    val isGoalNode: Boolean

    /**
      * Calculates all the possible successors
      *
      * @return An Array of Nodes containing all valid child states.
      */
    def getSuccessors: Array[Node[T]]

}
