package contacts.services

import contacts.io.{OutputWriter, InputReader}
import contacts.models.{Contact, Contacts}
import contacts.utils.Constants
import org.mockito._
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

class ContactsAppSpec extends Specification with Mockito {

  "ContactsApp" should {
    "launch app" in {
      val contacts = Contacts.empty

      "add contact" in {
        val inputReader = smartMock[InputReader]
        val outputWriter = smartMock[OutputWriter]
        val contactsService = smartMock[ContactsService]
        val contactsApp = new ContactsApp(inputReader, outputWriter, contactsService)

        inputReader.readInt(Constants.DisplayActions) returns 1 thenReturns 3

        contactsApp.launch(contacts)

        Mockito.verify(contactsService).addContactsTo(contacts)
        Mockito.verify(outputWriter).print(Constants.ExitMessage)
        endIn
      }

      "search contact" in {
        "show zero records message" in {
          val inputReader = smartMock[InputReader]
          val outputWriter = smartMock[OutputWriter]
          val contactsService = smartMock[ContactsService]

          val contactsApp = new ContactsApp(inputReader, outputWriter, contactsService)

          inputReader.readInt(Constants.DisplayActions) returns 2 thenReturns 3
          contactsService.searchContacts(contacts) returns contacts

          contactsApp.launch(contacts)

          Mockito.verify(contactsService).searchContacts(contacts)
          Mockito.verify(outputWriter).print(Constants.ZeroRecords)
          Mockito.verify(outputWriter).print(Constants.ExitMessage)
          endIn
        }

        "show matched contacts" in {
          val inputReader = smartMock[InputReader]
          val outputWriter = smartMock[OutputWriter]
          val contactsService = smartMock[ContactsService]
          val contact = Contact("Chris", Some("Harris"))
          val contacts = Contacts(Seq(contact))

          val contactsApp = new ContactsApp(inputReader, outputWriter, contactsService)

          inputReader.readInt(Constants.DisplayActions) returns 2 thenReturns 3
          contactsService.searchContacts(contacts) returns contacts

          contactsApp.launch(contacts)

          Mockito.verify(contactsService).searchContacts(contacts)
          Mockito.verify(outputWriter).print(contact.printableString)
          Mockito.verify(outputWriter).print(Constants.ExitMessage)
          endIn
        }
      }

      "not break in case of invalid option" in {
        val inputReader = smartMock[InputReader]
        val outputWriter = smartMock[OutputWriter]
        val contactsService = smartMock[ContactsService]
        val contactsApp = new ContactsApp(inputReader, outputWriter, contactsService)

        inputReader.readInt(Constants.DisplayActions) returns 7 thenReturns 3

        val contacts = Contacts.empty
        contactsApp.launch(contacts)

        Mockito.verify(outputWriter).print(Constants.InvalidOption)
        Mockito.verify(outputWriter).print(Constants.ExitMessage)
        endIn
      }
    }
  }

  def endIn = "" mustEqual ""
}
