package contacts.services

import contacts.io.{InputReader, OutputWriter}
import contacts.utils.Constants

class ContactsApp(inputReader: InputReader,
                  outputWriter: OutputWriter,
                  contactsService: ContactsService) {

  def launch {
    val input = inputReader.readInt(Constants.DisplayActions)

    if(input == 3)
      outputWriter.print(Constants.ExitMessage)
    else {
      performAction(input)
      launch
    }
  }

  private def performAction(input: Int) {
    input match {
      case 1 => contactsService.createContact
      case 2 => val filteredContacts = contactsService.searchContacts
                display(filteredContacts)
      case _ => outputWriter.print(Constants.InvalidOption);
    }
  }

  private def display(contacts: Seq[String]) = {
    if(contacts.isEmpty)
      outputWriter.print(Constants.ZeroRecords)

    contacts.foreach { contact =>
      outputWriter.print(contact)
    }
  }
}
