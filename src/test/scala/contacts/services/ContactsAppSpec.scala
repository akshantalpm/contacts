package contacts.services

import contacts.io.{InputReader, OutputWriter}
import contacts.utils.{Constants, Trie}
import org.mockito._
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

class ContactsAppSpec extends Specification with Mockito {

  "ContactsApp" should {
    "launch app" in {

      "add contact" in {
        val inputReader = smartMock[InputReader]
        val outputWriter = smartMock[OutputWriter]
        val contactsService = smartMock[ContactsService]
        val contactsApp = new ContactsApp(inputReader, outputWriter, contactsService)

        inputReader.readInt(Constants.DisplayActions) returns 1 thenReturns 3

        contactsApp.launch

        Mockito.verify(contactsService).createContact
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
          contactsService.searchContacts returns Seq.empty

          contactsApp.launch

          Mockito.verify(contactsService).searchContacts
          Mockito.verify(outputWriter).print(Constants.ZeroRecords)
          Mockito.verify(outputWriter).print(Constants.ExitMessage)
          endIn
        }

        "show matched contacts" in {
          val inputReader = smartMock[InputReader]
          val outputWriter = smartMock[OutputWriter]
          val contactsService = smartMock[ContactsService]
          val trie: Trie = Trie.root
          trie.append("Chris Harris")

          val contactsApp = new ContactsApp(inputReader, outputWriter, contactsService)

          inputReader.readInt(Constants.DisplayActions) returns 2 thenReturns 3
          contactsService.searchContacts returns Seq("Chris Harris")

          contactsApp.launch

          Mockito.verify(contactsService).searchContacts
          Mockito.verify(outputWriter).print("Chris Harris")
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

        contactsApp.launch

        Mockito.verify(outputWriter).print(Constants.InvalidOption)
        Mockito.verify(outputWriter).print(Constants.ExitMessage)
        endIn
      }
    }
  }

  def endIn = "" mustEqual ""
}
