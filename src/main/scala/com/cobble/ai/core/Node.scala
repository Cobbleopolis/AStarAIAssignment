package com.cobble.ai.core

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
      * @return An Array of Nodes containing all valid child states.
      */
    def getSuccessors: Array[Node[T]]

    /**
      * Used to compare two Nodes.
      * @param that The other Node to compare to
      * @return The difference of this evaluation function and that evaluation function.
      */
    def compare(that: Node[T]): Int = this.f - that.f

}
