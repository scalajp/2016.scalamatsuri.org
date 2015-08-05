package candidates

sealed trait Page

object Page {
  case class ListP(lang: Lang) extends Page
  case class DetailP(id: Int, lang: Lang) extends Page
}
