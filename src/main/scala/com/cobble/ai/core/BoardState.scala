package com.cobble.ai.core

abstract class BoardState[T, S <: State[S]](val board: Array[T]) extends State[S] {

    if (board.isEmpty)
        throw new IllegalArgumentException("Values cannot be empty")

    private val lengthSqrt: Double = Math.sqrt(board.length)
    val size: Int = lengthSqrt.toInt

    if (lengthSqrt - Math.floor(lengthSqrt) >= Double.MinPositiveValue)
        throw new IllegalArgumentException("Values length must be a perfect square")

    /**
      * Converts an index in the array to its (x, y) location.
      * @param index The index to get the location for
      * @return The (x, y) location for the provided index.
      */
    protected def indexToLocation(index: Int): (Int, Int) = (index % size, index / size)

    /**
      * Converts an (x, y) location to a location in the array.
      * @param x The x location of the index to find.
      * @param y The y location of the index to find.
      * @return The index in the array for the provided (x, y) location.
      */
    protected def locationToIndex(x: Int, y: Int): Int = y * size + x

    /**
      * Converts an (x, y) location to a location in the array.
      * @param loc The location of the index to find.
      * @return The index in the array for the provided (x, y) location.
      */
    protected def locationToIndex(loc: (Int, Int)): Int = locationToIndex(loc._1, loc._2)

    override def equals(obj: Any): Boolean = {
        obj match {
            case that: BoardState[T, S] => this.board.deep == that.board.deep && this.size == that.size && this.size == that.size
            case _ => false
        }
    }

    def toPrettyString: String = {
        val slotSize: Int = board.map(x => x.toString.length).max
        //noinspection ScalaMalformedFormatString
        board.map(x => s"%${slotSize}s".format(x.toString)).grouped(size).map(_.mkString(", ")).mkString("\n")
    }

}
