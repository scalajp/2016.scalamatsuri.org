package candidates

case class Candidate(
  id: Int,
  iconUrl: Option[String],
  twitter: Option[String],
  github: Option[String],
  audience: Audience,
  talkLength: TalkLength,
  lang: Lang,
  descriptions: Map[Lang, Description])

object Candidate {
  import Audience._
  import TalkLength._
  import Lang._

  val list: Seq[Candidate] = Seq(
    Candidate(1,
      Some("https://pbs.twimg.com/profile_images/378800000408766517/24f18a307fddbe5e5a15bea82df7eb06_400x400.jpeg"),
      Some("naoya_ito"),
      Some("naoya"),
      Beginner,
      Fifteen,
      Japanese,
      Map(
        Japanese -> Description(
          "伊藤直也",
          Some("Kaizen Platform, Inc."),
          "Introduction to Elixir",
          """Elixir は Erlang VM の上で動作する動的型付けの関数型言語です。
          |おや、Scala が Java VM の上で動作する〜、というのにそっくりですね。
          |
          |Elixir は Erlang/OTP の特徴をそのまま受け継いでおり、その中にはパターンマッチやアクターモデルによる並行処理など Scala プログラマにもお馴染みの機能/モデルが多数含まれます。
          |
          |Elixir は 2015 年昨今 Web 開発の Next big language だ、などと騒ぎになっています。果たしてその実力は?
          |
          |そんな Elixir の特徴を、Scala プログラマの皆さんに紹介し、その反応を見てみたいと思います。
        """.stripMargin
        )
      )
    ),
    Candidate(
      2,
      Some("https://avatars3.githubusercontent.com/u/615042"),
      Some("esehara"),
      Some("esehara"),
      Beginner,
      Fifteen,
      Japanese,
      Map(
        Japanese -> Description(
          "似非原重雄",
          Some("株式会社ログバー"),
          "Clojureから見たScala",
          """Clojureは、JVM上で動くプログラミング言語としては、Scalaと比較されることの多い言語です。確かに、Clojureは標準では動的型付け言語であり、どちらかといえば、型を厳密に書くScalaとは違う印象を持ちますが、しかし、比較してみると、お互いの共通点みたいなのが見えてきます。それは両者が、パラタイムとして「関数型言語」として括られることが多いからのように思えます。
          |
          |Clojureで仕事をし、Scalaは趣味で勉強している発表者が、その共通点を見ながら、「関数型プログラミング」について考えてみるのが、この発表の主旨となります。
        """.stripMargin
        )
      )
    )
  )
}
