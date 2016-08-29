package contacts

import contacts.io.{OutputWriter, InputReader}
import contacts.models.Contacts
import contacts.services.{ContactsService, ContactsApp}

object AppLauncher extends App {
  private val inputReader = new InputReader
  private val outputWriter = new OutputWriter
  private val contactsService = new ContactsService(inputReader)

  new ContactsApp(inputReader, outputWriter, contactsService).launch(Contacts.empty)
}
