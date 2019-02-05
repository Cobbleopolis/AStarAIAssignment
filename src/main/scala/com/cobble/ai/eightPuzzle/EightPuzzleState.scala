package com.cobble.ai.eightPuzzle

import com.cobble.ai.core.State

case class EightPuzzleState(m00: Int, m01: Int, m02: Int, m10: Int, m11: Int, m12: Int, m20: Int, m21: Int, m22: Int) extends State {

    def this(valueArray: Array[Int]) = this(
        valueArray(0), valueArray(1), valueArray(2),
        valueArray(3), valueArray(4), valueArray(5),
        valueArray(6), valueArray(7), valueArray(8)
    )

    lazy val getValuesAsArray: Array[Int] = Array(m00, m01, m02, m10, m11, m12, m20, m21, m22)

    def getValueLocation(value: Int): (Int, Int) = indexToLocation(getValuesAsArray.indexOf(value))

    private lazy val zeroIndex: Int = getValuesAsArray.indexOf(0)

    private lazy val zeroLocation: (Int, Int) = indexToLocation(zeroIndex)

    private def indexToLocation(index: Int): (Int, Int) = (index / 3, index % 3)

    private def locationToIndex(x: Int, y: Int): Int = x * 3 + y

    private def swapIndexes(index1: Int, index2: Int): EightPuzzleState = {
        val vArray = getValuesAsArray.clone
        val tmp = vArray(index2)
        vArray(index2) = vArray(index1)
        vArray(index1) = tmp
        EightPuzzleState(vArray)
    }

    private def swapLocation(x1: Int, y1: Int, x2: Int, y2: Int): EightPuzzleState = {
        swapIndexes(locationToIndex(x1, y1), locationToIndex(x2, y2))
    }

    override val isValid: Boolean = (0 to 8).forall(getValuesAsArray.contains)

    override def getSuccessors: Array[State] = EightPuzzleAction.values.map(applyAction).filterNot(_.isEmpty).map(_.get).filter(_.isValid).toArray

    override def applyAction[EightPuzzleAction](action: EightPuzzleAction): Option[State] = {
        val stateOpt: Option[State] = action match {
            case EightPuzzleAction.MOVE_UP =>
                zeroLocation match {
                    case (0, _) => None
                    case (x, y) => Some(swapLocation(x, y, x - 1, y))
                }
            case EightPuzzleAction.MOVE_DOWN =>
                zeroLocation match {
                    case (2, _) => None
                    case (x, y) => Some(swapLocation(x, y, x + 1, y))
                }
            case EightPuzzleAction.MOVE_LEFT =>
                zeroLocation match {
                    case (_, 0) => None
                    case (x, y) => Some(swapLocation(x, y, x, y - 1))
                }
            case EightPuzzleAction.MOVE_RIGHT =>
                zeroLocation match {
                    case (_, 2) => None
                    case (x, y) => Some(swapLocation(x, y, x, y + 1))
                }
            case _ => None
        }
        if (stateOpt.isDefined && !stateOpt.get.isValid) None else stateOpt
    }

    def toPrettyString: String = s"$m00, $m01, $m02,\n$m10, $m11, $m12,\n$m20, $m21, $m22"
}

object EightPuzzleState {
    def apply(valueArray: Array[Int]) = new EightPuzzleState(valueArray)
}
