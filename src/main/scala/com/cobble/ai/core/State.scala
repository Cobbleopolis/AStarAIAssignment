package com.cobble.ai.core

abstract class State {

    val isValid: Boolean

    def applyAction[T <: Action](action: T): Option[State]

    def getSuccessors: Array[State]

}
