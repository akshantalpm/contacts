package contacts
package services

import contacts.io.InputReader
import contacts.models.{Contact, Contacts}
import contacts.utils.Constants

class ContactsService(inputReader: InputReader) {

  def addContactsTo(contacts: Contacts) = try {
    val name = inputReader.readLine(Constants.PromptName)
    val contact = Contact.create(name)
    contacts.addContact(contact)
  } catch {
    case ex: RuntimeException => println(ex.getMessage)
      contacts
  }

  def searchContacts(contacts: Contacts) = {
    val contact = inputReader.readLine(Constants.PromptName)
    contacts.searchContact(contact)
  }
}
