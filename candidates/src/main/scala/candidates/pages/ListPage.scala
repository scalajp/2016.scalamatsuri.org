package candidates.pages

import candidates.Page.DetailP
import candidates.{ Page, Lang, Candidate }
import japgolly.scalajs.react.ReactComponentB
import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.extra.router2.RouterCtl

object ListPage {
  case class Props(lang: Lang, ctl: RouterCtl[Page])

  val component = ReactComponentB[Props]("Candidates list")
    .render { p =>
      def createLi(candidate: Candidate) = {
        val desc = candidate.descriptions(p.lang)
        <.li(
          p.ctl.link(DetailP(candidate.id, p.lang))(
            desc.title
          )
        )
      }

      <.ul(Candidate.list.filter(_.descriptions.contains(p.lang)).map(createLi))
    }.build

  def apply(lang: Lang, ctl: RouterCtl[Page]) = component(Props(lang, ctl))
}
