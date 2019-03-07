package com.cobble.ai.eightPuzzle

import com.cobble.ai.core.BoardState

case class EightPuzzleState(override val board: Array[Int]) extends BoardState[Int, EightPuzzleState](board) {

    def getValueLocation(value: Int): (Int, Int) = indexToLocation(board.indexOf(value))

    private lazy val zeroIndex: Int = board.indexOf(0)

    private lazy val zeroLocation: (Int, Int) = indexToLocation(zeroIndex)

    private def swapIndexes(index1: Int, index2: Int): EightPuzzleState = {
        val vArray = board.clone
        val tmp = vArray(index2)
        vArray(index2) = vArray(index1)
        vArray(index1) = tmp
        EightPuzzleState(vArray)
    }

    private def swapLocation(x1: Int, y1: Int, x2: Int, y2: Int): EightPuzzleState = {
        swapIndexes(locationToIndex(x1, y1), locationToIndex(x2, y2))
    }

    override val isValid: Boolean = board.indices.forall(board.contains)

    override def applyAction[EightPuzzleAction](action: EightPuzzleAction): Option[EightPuzzleState] = {
        val stateOpt: Option[EightPuzzleState] = action match {
            case EightPuzzleAction.MOVE_UP =>
                zeroLocation match {
                    case (_, y) if y <= 0 => None
                    case (x, y) => Some(swapLocation(x, y, x, y - 1))
                }
            case EightPuzzleAction.MOVE_DOWN =>
                zeroLocation match {
                    case (_, y) if y >= size - 1 => None
                    case (x, y) => Some(swapLocation(x, y, x, y + 1))
                }
            case EightPuzzleAction.MOVE_LEFT =>
                zeroLocation match {
                    case (x, _) if x <= 0 => None
                    case (x, y) => Some(swapLocation(x, y, x - 1, y))
                }
            case EightPuzzleAction.MOVE_RIGHT =>
                zeroLocation match {
                    case (x, _) if x >= size - 1 => None
                    case (x, y) => Some(swapLocation(x, y, x + 1, y))
                }
            case _ => None
        }
        if (stateOpt.isDefined && !stateOpt.get.isValid) None else stateOpt
    }
}
