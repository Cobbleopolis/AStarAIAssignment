package com.cobble.ai.eightPuzzle

import com.cobble.ai.core.Node
import com.cobble.ai.eightPuzzle.EightPuzzleAction.EightPuzzleAction

case class EightPuzzleNode(
                              override val state: EightPuzzleState,
                              goalState: EightPuzzleState,
                              action: Option[EightPuzzleAction] = None,
                              override val parent: Option[EightPuzzleNode] = None
                          ) extends Node[EightPuzzleNode, EightPuzzleState](state, parent) {

    val h: Int = manhattanDistanceHeuristic

    override val isGoalNode: Boolean = state == goalState

    def numDisplacedHeuristic: Int = {
        state.board.zip(goalState.board).count(x => x._1 != x._2)
    }

    def manhattanDistanceHeuristic: Int = {
        (0 to 8).map(v => {
            val stateLoc: (Int, Int) = state.getValueLocation(v)
            val goalLoc: (Int, Int) = goalState.getValueLocation(v)
            Math.abs(stateLoc._1 - goalLoc._1) + Math.abs(stateLoc._2 - goalLoc._2)
        }).sum
    }

    override def getSuccessors: Array[EightPuzzleNode] = EightPuzzleAction.values
        .map(a => (a, state.applyAction(a)))
        .filter(_._2.isDefined)
        .map(x => (x._1, x._2.get))
        .filter(_._2.isValid)
        .map(s => EightPuzzleNode(s._2, goalState, Some(s._1), Some(this)))
        .toArray

    override def equals(obj: Any): Boolean = {
        obj match {
            case n: EightPuzzleNode => this.state == n.state
            case _ => false
        }
    }
}
