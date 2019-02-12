package com.cobble.ai.nqueen

import com.cobble.ai.core.{Action, BoardState, State}

case class NQueenState(override val board: Array[Byte]) extends BoardState[Byte](board) {

    /**
      * Determines if a location is occupied by a queen or not.
      * @param x The x location to check.
      * @param y The y location to check.
      * @return True if the location in the array is occupied by a queen. False if it not or out of the bounds of the board.
      */
    private def isLocationOccupied(x: Int, y: Int): Boolean = x <= 0 || y <= 0 || x >= size || y >= size || board(locationToIndex(x, y)) == 1

    lazy val queenLocations: Array[(Int, Int)] = board.zipWithIndex.filter(_._1 == 1).map(x => indexToLocation(x._1))

    lazy val queenCount: Int = board.count(_ == 1)

    override val isValid: Boolean = queenLocations.forall(loc => isLocationSafe(loc._1, loc._2))

    private def isLocationSafe(x: Int, y: Int): Boolean = checkPawnRange(x, y) && checkRookRange(x, y) && checkBishopRange(x, y)

    private def checkPawnRange(x: Int, y: Int): Boolean = {
        isLocationOccupied(x - 1, y - 1) && isLocationOccupied(x    , y - 1) && isLocationOccupied(x + 1, y - 1) &&
        isLocationOccupied(x - 1, y    ) &&                                     isLocationOccupied(x + 1, y    ) &&
        isLocationOccupied(x - 1, y + 1) && isLocationOccupied(x    , y + 1) && isLocationOccupied(x + 1, y + 1)
    }

    private def checkRookRange(x: Int, y: Int): Boolean = ???

    private def checkBishopRange(x: Int, y: Int): Boolean = ???

    override def applyAction[A <: Action](action: A): Option[NQueenState] = {
        action match {
            case nQueenAction: NQueenAction =>
                val boardCopy: Array[Byte] = board.clone
                boardCopy(locationToIndex(nQueenAction.row, nQueenAction.col)) = 1
                Some(NQueenState(boardCopy))
            case _ => None
        }
    }

    override def getSuccessors: Array[State] = Array() //TODO
}
