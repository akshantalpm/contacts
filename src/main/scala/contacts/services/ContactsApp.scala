package contacts.services

import contacts.io.{OutputWriter, InputReader}
import contacts.models.Contacts
import contacts.utils.Constants

class ContactsApp(inputReader: InputReader,
                  outputWriter: OutputWriter,
                  contactsService: ContactsService) {

  def launch(contacts: Contacts) {
    val input = inputReader.readInt(Constants.DisplayActions)

    if(input == 3)
      outputWriter.print(Constants.ExitMessage)
    else
      launch(performAction(contacts, input).getOrElse(contacts))
  }

  private def performAction(contacts: Contacts, input: Int) = {
    input match {
      case 1 => Some(contactsService.addContactsTo(contacts))
      case 2 => val filteredContacts = contactsService.searchContacts(contacts)
                display(filteredContacts)
                Some(contacts);
      case _ => outputWriter.print(Constants.InvalidOption); None
    }
  }

  private def display(contacts: Contacts) = {
    if(contacts.isEmpty)
      outputWriter.print(Constants.ZeroRecords)

    contacts.contacts.foreach { contact =>
      outputWriter.print(contact.printableString)
    }
  }
}
