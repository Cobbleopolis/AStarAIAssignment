package com.cobble.ai.nqueen

import com.cobble.ai.core.Node

case class NQueenNode(
                         override val state: NQueenState,
                         goalQueenCount: Int,
                         override val parent: Option[NQueenNode] = None
                     ) extends Node[NQueenNode, NQueenState](state, parent) {

    override val h: Int = goalQueenCount - state.queenLocations.length //TODO see if it can't be made any better

    override val isGoalNode: Boolean = state.queenCount == goalQueenCount

    override def getSuccessors: Array[NQueenNode] = state.getSuccessors.map(s => NQueenNode(s, goalQueenCount, Some(this)))
}
