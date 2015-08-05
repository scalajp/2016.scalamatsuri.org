package candidates

sealed trait Audience

object Audience {
  case object Beginner extends Audience
  case object Intermediate extends Audience
  case object Advanced extends Audience
}
