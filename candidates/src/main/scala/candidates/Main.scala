package candidates

import candidates.Lang.{ English, Japanese }
import candidates.pages.{ ListPage, DetailPage }
import candidates.Page._
import japgolly.scalajs.react.extra.router2.StaticDsl.RouteB
import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.{ React, ReactComponentB }
import japgolly.scalajs.react.extra.router2.{ Router, RouterConfigDsl, BaseUrl, Redirect }
import org.scalajs.dom

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object Main extends JSApp {
  val langB = new RouteB[Lang]("(ja|en)", 1, g => Lang.valueOf(g(0)), _.string)

  @JSExport
  override def main(): Unit = {

    val routerConfig = RouterConfigDsl[Page].buildConfig { dsl =>
      import dsl._

      (
        dynamicRouteCT(("#list" / langB).caseclass1(ListP.apply)(ListP.unapply)) ~>
        dynRenderR { case (ListP(l), ctl) => ListPage(l, ctl) } |

        dynamicRouteCT(("#" ~ int / langB).caseclass2(DetailP.apply)(DetailP.unapply)) ~>
        dynRenderR { case (DetailP(id, l), ctl) => DetailPage(l, id, ctl) }

      )
        .notFound(redirectToPage(ListP(English))(Redirect.Replace))
    }

    val baseUrl = BaseUrl.fromWindowOrigin / "candidates/"
    val router = Router(baseUrl, routerConfig)

    React.render(router(), dom.document.getElementById("candidates"))
  }
}
