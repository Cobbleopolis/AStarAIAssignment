package com.cobble.ai.nqueen

import com.cobble.ai.core.Node

case class NQueenNode(state: NQueenState, goalQueenCount: Int, parent: Option[NQueenNode] = None) extends Node[NQueenState](state, parent) {

    override val h: Int = goalQueenCount - state.queenLocations.length //TODO see if it can't be made any better

    override val isGoalNode: Boolean = state.queenCount == goalQueenCount

    override def getSuccessors: Array[Node[NQueenState]] = state.getSuccessors.map(s => NQueenNode(s.asInstanceOf[NQueenState], goalQueenCount, Some(this)))
}
