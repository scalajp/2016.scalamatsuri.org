package candidates

sealed abstract class Lang(val string: String)

object Lang {
  case object Japanese extends Lang("ja")
  case object English extends Lang("en")

  def values: Seq[Lang] = Seq(Japanese, English)

  def valueOf(s: String): Option[Lang] = values.find(_.string == s)
}
