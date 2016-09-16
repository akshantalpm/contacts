package contacts.services

import contacts.collection.mutable.Trie
import contacts.io.InputReader
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

class ContactsServiceSpec extends Specification with Mockito {
  "ContactService" should {
    "create contact" in {
      val inputReader = smartMock[InputReader]
      val contactService = new ContactsService(inputReader)
      inputReader.readLine("Enter name:") returns "Chris Harris"

      contactService.createContact

      val expectedTrie: Trie = Trie.root
      expectedTrie.append("Chris Harris")

      contactService.contacts mustEqual expectedTrie
    }

    "not throw exception if create contact fails" in {
      val inputReader = smartMock[InputReader]
      val contactService = new ContactsService(inputReader)
      inputReader.readLine("Enter name:") returns "Chris B Harris"

      contactService.createContact

      contactService.contacts mustEqual Trie.root
    }

    "search contact for given name" in {
      val inputReader = smartMock[InputReader]
      val contactService = new ContactsService(inputReader)
      inputReader.readLine("Enter name:") returns ("Chris Blah", "Blah Harris", "Chris")

      contactService.createContact
      contactService.createContact

      contactService.searchContacts.toList mustEqual Seq("Chris Blah")
    }
  }
}
