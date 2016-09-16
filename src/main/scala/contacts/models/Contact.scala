package contacts.models

import contacts.utils.{AppConfig, Constants}

case class Contact(firstName: String, lastName: Option[String]) {
  def full_name = lastName match {
    case Some(lastName) => s"$firstName $lastName"
    case None => firstName
  }
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