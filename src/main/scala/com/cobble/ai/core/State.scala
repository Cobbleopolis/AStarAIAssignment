package com.cobble.ai.core

/**
  * An abstract class describing the current state of a problem.
  */
abstract class State {

    /**
      * True if the current state is valid. False otherwise.
      */
    val isValid: Boolean

    /**
      * Applies an action to this state.
      *
      * @param action The action to be applied.
      * @tparam T The type of the Action being applied.
      * @return The optional resulting state from applying the action. Some if the action can be applied to the current state. False otherwise
      */
    def applyAction[T <: Action](action: T): Option[State]

    def getSuccessors: Array[State]

}
