package server

import shared.Api

object ApiImpl extends Api {

  //TODO: should not be hardcoded
  private val suggestionsList = Seq(
    "Wake up early tomorrow",
    "Walk the dog",
    "Talk to kids",
    "Do more Scala")

  def suggestions(subString: String) =
    suggestionsList.filter(_.toLowerCase.contains(subString.toLowerCase))

  def aaa(subString: String) = s"substring: $subString"
}