package com.cobble.ai.eightPuzzle

import com.cobble.ai.core.Node

case class EightPuzzleNode(state: EightPuzzleState, goalState: EightPuzzleState, parent: Option[Node[EightPuzzleState]] = None) extends Node[EightPuzzleState](state, parent) {

    val h: Int = numDisplacedHeuristic

    override val isGoalNode: Boolean = state == goalState

    def numDisplacedHeuristic: Int = {
        state.getValuesAsArray.zip(goalState.getValuesAsArray).count(x => x._1 != x._2)
    }

    override def getSuccessors: Array[Node[EightPuzzleState]] = state.getSuccessors.map(s => EightPuzzleNode(s.asInstanceOf[EightPuzzleState], goalState, Some(this)))
}
