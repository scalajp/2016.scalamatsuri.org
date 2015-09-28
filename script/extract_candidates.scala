#!/usr/bin/env scalas

/***
scalaVersion := "2.11.7"

resolvers += Resolver.typesafeIvyRepo("releases")

libraryDependencies += "org.scala-sbt" % "io" % "0.13.9"
*/

import sbt._, Path._
import java.io.File
import java.net.{URI, URL}
def file(s: String): File = new File(s)
def uri(s: String): URI = new URI(s)

val baseDirectory = file(".")

sealed trait Lang
case object En extends Lang
case object Ja extends Lang

sealed trait Audience
case object Beginner extends Audience
case object Intermediate extends Audience
case object Advanced extends Audience

def extractCandidates(lang: Lang): List[String] = {
  val dir = baseDirectory / s"_candidates_${lang.toString.toLowerCase}"
  val fs: List[File] = (dir ** "*.html").get.toList
  val cs = fs map { parseCandidate(_) }
  categoryStr(lang, cs, 40, En) :::
  categoryStr(lang, cs, 40, Ja) :::
  categoryStr(lang, cs, 15, En) :::
  categoryStr(lang, cs, 15, Ja)
}
def categoryStr(lang: Lang, cs: List[Candidate], length: Int, sessionLang: Lang): List[String] =
  List(s"## $length min $sessionLang sessions") :::
  (cs collect {
    case c@Candidate(_, _, `length`, _, `sessionLang`) => c.item(lang)
  }) :::
  List("")
case class Candidate(name: String, title: String, length: Int,
  audience: Audience, sessionLang: Lang) {
  def item(lang: Lang): String = s"$title ($name) " + audienceStr(lang)
  def audienceStr(lang: Lang): String =
    audience match {
      case Beginner     => "+"
      case Intermediate => "++"
      case Advanced     => "+++"
    }
}
def parseCandidate(f: File): Candidate = {
  val xs: List[String] = IO.readLines(f) map { _.trim }
  xs.headOption match {
    case Some("---") => //
    case x => sys.error(s"expected --- but '$x'")
  }
  val attr = Map(xs.tail.takeWhile(_ != "---") map { s =>
    val (n, v) = s.splitAt(s.indexOf(':'))
    n.trim -> unquote(v.tail.trim)
  }: _*)
  Candidate(attr("name"),
    attr("title"),
    attr("length").toInt match {
      case 40 => 40
      case 15 => 15
    },
    attr("audience") match {
      case "Beginner"     => Beginner
      case "Intermediate" => Intermediate
      case "Advanced"     => Advanced
    },
    attr("language") match {
      case "English"  => En
      case "Japanese" => Ja
    })
}
def unquote(s: String): String =
  s.headOption match {
    case Some('"') if s.last == '"' => s.tail.init
    case _ => s
  }

println((extractCandidates(En) ::: extractCandidates(Ja)).mkString("\n"))
