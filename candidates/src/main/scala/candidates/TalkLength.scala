package candidates

sealed trait TalkLength

object TalkLength {
  /** 40 minutes */
  case object Forty extends TalkLength

  /** 15 minutes */
  case object Fifteen extends TalkLength
}
