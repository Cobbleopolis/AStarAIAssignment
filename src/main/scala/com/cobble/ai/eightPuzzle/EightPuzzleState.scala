package com.cobble.ai.eightPuzzle

import com.cobble.ai.core.State

case class EightPuzzleState(m00: Int, m01: Int, m02: Int, m10: Int, m11: Int, m12: Int, m20: Int, m21: Int, m22: Int) extends State {

    def this(valueArray: Array[Int]) = this(
        valueArray(0), valueArray(1), valueArray(2),
        valueArray(3), valueArray(4), valueArray(5),
        valueArray(6), valueArray(7), valueArray(8)
    )

    lazy val getValuesAsArray: Array[Int] = Array(m00, m01, m02, m10, m11, m12, m20, m21, m22)

    lazy val zeroIndex: Int = getValuesAsArray.indexOf(0)

    lazy val zeroLocation: (Int, Int) = indexToLocation(zeroIndex)

    private def indexToLocation(index: Int): (Int, Int) = (index / 3, index % 3)

    private def locationToIndex(x: Int, y: Int): Int = x * 3 + y

    private def swapIndexes(index1: Int, index2: Int): EightPuzzleState = {
        val tmp = getValuesAsArray(index2)
        getValuesAsArray(index2) = getValuesAsArray(index1)
        getValuesAsArray(index1) = tmp
        EightPuzzleState(getValuesAsArray)
    }

    private def swapLocation(x1: Int, y1: Int, x2: Int, y2: Int): EightPuzzleState = swapIndexes(locationToIndex(x1, y1), locationToIndex(x2, y2))

    override val isValid: Boolean = (0 to 8).forall(getValuesAsArray.contains)

    override def getSuccessors: Array[State] = Array()

    override def applyAction[EightPuzzleAction](action: EightPuzzleAction): Option[State] = {
        action match {
            case EightPuzzleAction.MOVE_UP =>
                zeroLocation match {
                    case (0, _) => None
                    case (x, y) => Some(swapLocation(x, y, x - 1, y))
                }
            case _ => None
        }
    }
}

object EightPuzzleState {
    def apply(valueArray: Array[Int]) = new EightPuzzleState(valueArray)
}
