package com.cobble.ai.core

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

abstract class Problem[S <: State, N <: Node[S]](implicit c: ClassTag[N]) {

    val initialNode: N

    def findSolution(): Option[N] = {
        val closedSet: ArrayBuffer[N] = ArrayBuffer[N]()
        var openSet: Array[N] = Array[N](initialNode)
        while (openSet.nonEmpty) {
            val current: N = openSet.head
            if (current.isGoalNode)
                return Some(current)
            openSet = openSet.tail
            closedSet += current
            openSet = (openSet ++ current.getSuccessors.map(_.asInstanceOf[N]).filterNot(closedSet.contains)).sortBy(_.f)
        }
        None
    }

}
