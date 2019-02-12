package com.cobble.ai.nqueen

import com.cobble.ai.core.{Action, BoardState}

case class NQueenState(override val board: Array[Byte]) extends BoardState[Byte, NQueenState](board) {

    /**
      * Determines if a location is occupied by a queen or not.
      *
      * @param x The x location to check.
      * @param y The y location to check.
      * @return True if the location in the array is occupied by a queen. False if it not or out of the bounds of the board.
      */
    def isLocationOccupied(x: Int, y: Int): Boolean = x >= 0 && y >= 0 && x < size && y < size && board(locationToIndex(x, y)) == 1

    /**
      * An array of tuples defining where all the queens are located.
      */
    lazy val queenLocations: Array[(Int, Int)] = board.zipWithIndex.filter(_._1 == 1).map(x => indexToLocation(x._1))

    /**
      * The number of queens in this state
      */
    lazy val queenCount: Int = board.count(_ == 1)

    override val isValid: Boolean = queenLocations.forall(loc => isLocationSafe(loc._1, loc._2))

    /**
      * If the location in the board is not able to be attacked by any of the queens present.
      *
      * @param x The x location of the position to check.
      * @param y The y location of the position to check.
      * @return True if the location is not able to be attacked by any of the queens on the board. False, otherwise.
      */
    def isLocationSafe(x: Int, y: Int): Boolean = checkPawnRange(x, y) && checkRookRange(x, y) && checkBishopRange(x, y)

    /**
      * Checks to see if a location is safe from any queens within pawn range.
      *
      * @param x The x location of the position to check.
      * @param y The y location of the position to check.
      * @return True if the location is safe. False, otherwise.
      */
    private def checkPawnRange(x: Int, y: Int): Boolean = {
        !(isLocationOccupied(x - 1, y - 1) || isLocationOccupied(x, y - 1) || isLocationOccupied(x + 1, y - 1) ||
            isLocationOccupied(x - 1, y) || isLocationOccupied(x + 1, y) ||
            isLocationOccupied(x - 1, y + 1) || isLocationOccupied(x, y + 1) || isLocationOccupied(x + 1, y + 1))
    }

    /**
      * Checks to see if a location is safe from any queens within rook range.
      *
      * @param x The x location of the position to check.
      * @param y The y location of the position to check.
      * @return True if the location is safe. False, otherwise.
      */
    private def checkRookRange(x: Int, y: Int): Boolean = (0 until size).forall(cx => (x == cx || !isLocationOccupied(cx, y)) && (y == cx || !isLocationOccupied(x, cx)))

    /**
      * Checks to see if a location is safe from any queens within bishop range.
      *
      * @param x The x location of the position to check.
      * @param y The y location of the position to check.
      * @return True if the location is safe. False, otherwise.
      */
    private def checkBishopRange(x: Int, y: Int): Boolean = {
        (0 to size).forall(i => {
            val diff = i - x
            diff == 0 || !(isLocationOccupied(i, y - diff) && isLocationOccupied(i, y + diff))
        })
    }

    lazy val nextClearColumn: Int = (0 until size).indexWhere(x => (0 until size).forall(y => !isLocationOccupied(x, y)))

    def getSafeLocationsInColumn(x: Int): Array[(Int, Int)] = (0 until size).map((x, _)).filter(loc => isLocationSafe(loc._1, loc._2)).toArray

    override def applyAction[A <: Action](action: A): Option[NQueenState] = {
        action match {
            case nQueenAction: NQueenAction =>
                val boardCopy: Array[Byte] = board.clone
                boardCopy(locationToIndex(nQueenAction.row, nQueenAction.col)) = 1
                Some(NQueenState(boardCopy))
            case _ => None
        }
    }

    override def getSuccessors: Array[NQueenState] = {
        getSafeLocationsInColumn(nextClearColumn).map(loc => applyAction(NQueenAction(loc._1, loc._2))).filter(_.isDefined).map(_.get).filter(_.isValid)
    }

    override def toPrettyString: String = {
        board.map {
            case 1 => "Q"
            case 0 => "_"
            case _ => "?"
        }.grouped(size).map(_.mkString(" ")).mkString("\n")
    }

}
