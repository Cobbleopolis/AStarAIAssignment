package com.cobble.ai.core

/**
  * An abstract class describing the current state of a problem.
  *
  * @tparam S The type of the implementing State.
  */
abstract class State[S <: State[S]] {

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
    def applyAction[T <: Action](action: T): Option[S]

}
