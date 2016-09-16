package contacts
package services

import contacts.io.InputReader
import contacts.models.Contact
import contacts.utils.{Constants, Trie}

class ContactsService(inputReader: InputReader) {

  val contacts = new Trie(None, "", false)

  def createContact = try {
    val name = inputReader.readLine(Constants.PromptName)
    contacts.append(Contact.create(name).full_name)
  } catch {
    case ex: RuntimeException => println(ex.getMessage)
      contacts
  }

  def searchContacts = {
    val contact = inputReader.readLine(Constants.PromptName)
    contacts.findByPrefix(contact)
  }
}
