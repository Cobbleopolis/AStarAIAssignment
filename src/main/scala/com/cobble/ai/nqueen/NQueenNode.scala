package com.cobble.ai.nqueen

import com.cobble.ai.core.Node

case class NQueenNode(
                         override val state: NQueenState,
                         goalQueenCount: Int,
                         override val parent: Option[NQueenNode] = None
                     ) extends Node[NQueenNode, NQueenState](state, parent) {

    override val h: Int = goalQueenCount - state.queenLocations.length //TODO see if it can't be made any better

    override val isGoalNode: Boolean = state.queenCount == goalQueenCount

    override def getSuccessors: Array[NQueenNode] = state.getSafeLocationsInColumn(state.nextClearColumn)
        .map(loc => state.applyAction(NQueenAction(loc._1, loc._2)))
        .filter(_.isDefined)
        .map(_.get)
        .filter(_.isValid)
        .map(NQueenNode(_, goalQueenCount, Some(this)))
}
