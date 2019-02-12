package com.cobble.ai.nqueen

import com.cobble.ai.core.{Action, State}

case class NQueenState(board: Array[Byte]) extends State {

    if (board.isEmpty)
        throw new IllegalArgumentException("Values cannot be empty")

    private val lengthSqrt: Double = Math.sqrt(board.length)
    val size: Int = lengthSqrt.toInt

    if (lengthSqrt - Math.floor(lengthSqrt) >= Double.MinPositiveValue)
        throw new IllegalArgumentException("Values length must be a perfect square")

    private def indexToLocation(index: Int): (Int, Int) = (index % size, index / size)

    private def locationToIndex(x: Int, y: Int): Int = y * size + x

    lazy val queenLocations: Array[(Int, Int)] = board.zipWithIndex.filter(_._1 == 1).map(x => indexToLocation(x._1))

    lazy val queenCount: Int = board.count(_ == 1)

    override val isValid: Boolean = queenLocations.forall(loc => isLocationSafe(loc._1, loc._2))

    private def isLocationSafe(x: Int, y: Int): Boolean = true //TODO

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
