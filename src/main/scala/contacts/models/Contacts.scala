package contacts.models

case class Contacts(contacts: Seq[Contact]) {
  def addContact(contact: Contact) = Contacts(contacts :+ contact)

  def searchContact(contact: String) = {
    val filteredContacts = contacts.filter(_.isMatch(contact.toLowerCase))
    Contacts(filteredContacts)
  }

  def isEmpty = contacts.isEmpty
}

object Contacts {
  val empty = Contacts(Seq.empty)
}
