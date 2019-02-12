package com.cobble.ai.eightPuzzle

import com.cobble.ai.core.Action

object EightPuzzleAction extends Enumeration with Action {
    type EightPuzzleAction = Value
    val MOVE_UP: EightPuzzleAction = Value("MOVE_UP")
    val MOVE_DOWN: EightPuzzleAction = Value("MOVE_DOWN")
    val MOVE_LEFT: EightPuzzleAction = Value("MOVE_LEFT")
    val MOVE_RIGHT: EightPuzzleAction = Value("MOVE_RIGHT")
}
