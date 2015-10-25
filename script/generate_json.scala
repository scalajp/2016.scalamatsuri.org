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

def printJson(): Unit = {
  println(extractCandidates.mkString("\n"))
}
def extractCandidates: List[String] = {
  val dirEn = baseDirectory / "_candidates_en"
  val dirJa = baseDirectory / "_candidates_ja"
  val fsEn: List[File] = (dirEn ** "*.html").get.toList
  val fsJa: List[File] = (dirJa ** "*.html").get.toList
  val csEn = fsEn map { parseCandidate(_) }
  val csJa = fsJa map { parseCandidate(_) }
  jsonLines(csEn, csJa, 40, En) :::
  jsonLines(csEn, csJa, 40, Ja) :::
  jsonLines(csEn, csJa, 15, En) :::
  jsonLines(csEn, csJa, 15, Ja)
}
def jsonLine(idx: Int, c1: Candidate, c2: Candidate): String =
  s"""{ "id": "${c1.sessionLang}${c1.length}_$idx", """ +
  s""""en_title": "${c1.item(En)}", """ +
  s""""ja_title": "${c2.item(Ja)}" },"""
def jsonLines(cs1: List[Candidate], cs2: List[Candidate], length: Int, sessionLang: Lang): List[String] =
  zipCategory(cs1, cs2, length, sessionLang).zipWithIndex map {
    case ((a, b), idx) => jsonLine(idx, a, b)
  }
def zipCategory(cs1: List[Candidate], cs2: List[Candidate], length: Int, sessionLang: Lang) =
  category(cs1, length, sessionLang) zip category(cs2, length, sessionLang)
def category(cs: List[Candidate], length: Int, sessionLang: Lang): List[Candidate] =
  cs collect {
    case c@Candidate(_, _, `length`, _, `sessionLang`) => c
  }
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

// println((extractCandidates(En) ::: extractCandidates(Ja)).mkString("\n"))

printJson()
