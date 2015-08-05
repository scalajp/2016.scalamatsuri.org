package candidates

/** Description of a candidate (language dependent) */
case class Description(
  name: String,
  organization: Option[String],
  title: String,
  abst: String)
