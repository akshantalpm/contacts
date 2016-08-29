package contacts.models

import contacts.utils.{AppConfig, Constants}

case class Contact(firstName: String, lastName: Option[String]) {
  def printableString = s"$firstName ${lastName.getOrElse("")}"

  def isMatch(criteria: String) = firstName.toLowerCase.contains(criteria) || (lastName.isDefined && lastName.get.toLowerCase.contains(criteria))
}

object Contact {
  def create(contact: String) = {
    if(contact.trim.length > AppConfig.ContactLengthLimit)
      throw new RuntimeException(Constants.ContactLengthLimitExceeded)

    contact.trim.split(" ").filterNot(_ == "") match {
      case Array(firstName, lastName) => Contact(firstName.trim, Some(lastName.trim))
      case Array(firstName) => Contact(firstName.trim, None)
      case _ => throw new RuntimeException(Constants.InvalidContactFormat)
    }
  }
}