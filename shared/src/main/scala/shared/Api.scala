package shared

import shared.Api.Suggestion

//import shared.Api.Suggestion

trait Api {

  def suggestions(subString: String): Seq[Suggestion]
  def aaa(subString: String): String
}

object Api {
  type Suggestion = String
}