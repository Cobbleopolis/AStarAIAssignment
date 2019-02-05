package com.cobble.ai.eightPuzzle

import com.cobble.ai.core.State

case class EightPuzzleState(values: Array[Int]) extends State {

    if (values.isEmpty)
        throw new IllegalArgumentException("Values cannot be empty")

    private val lengthSqrt: Double = Math.sqrt(values.length)
    val size: Int = lengthSqrt.toInt

    if (lengthSqrt - Math.floor(lengthSqrt) >= Double.MinPositiveValue)
        throw new IllegalArgumentException("Values length must be a perfect square")

    def getValueLocation(value: Int): (Int, Int) = indexToLocation(values.indexOf(value))

    private lazy val zeroIndex: Int = values.indexOf(0)

    private lazy val zeroLocation: (Int, Int) = indexToLocation(zeroIndex)

    private def indexToLocation(index: Int): (Int, Int) = (index % size, index / size)

    private def locationToIndex(x: Int, y: Int): Int = y * size + x

    private def swapIndexes(index1: Int, index2: Int): EightPuzzleState = {
        val vArray = values.clone
        val tmp = vArray(index2)
        vArray(index2) = vArray(index1)
        vArray(index1) = tmp
        EightPuzzleState(vArray)
    }

    private def swapLocation(x1: Int, y1: Int, x2: Int, y2: Int): EightPuzzleState = {
        swapIndexes(locationToIndex(x1, y1), locationToIndex(x2, y2))
    }

    override val isValid: Boolean = values.indices.forall(values.contains)

    override def getSuccessors: Array[State] = EightPuzzleAction.values.map(applyAction).filterNot(_.isEmpty).map(_.get).filter(_.isValid).toArray

    override def applyAction[EightPuzzleAction](action: EightPuzzleAction): Option[State] = {
        val stateOpt: Option[State] = action match {
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

    override def equals(obj: Any): Boolean = {
        obj match {
            case that: EightPuzzleState => this.values.deep == that.values.deep && this.size == that.size && this.size == that.size
            case _ => false
        }
    }

    def toPrettyString: String = {
        val slotSize: Int = values.map(x => x.toString.length).max
        values.map(x => s"%${slotSize}s".format(x.toString)).grouped(size).map(_.mkString(", ")).mkString("\n")
    }
}
