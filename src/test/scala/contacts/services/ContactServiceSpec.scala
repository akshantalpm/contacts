package contacts.services

import contacts.io.InputReader
import contacts.models.{Contact, Contacts}
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

class ContactServiceSpec extends Specification with Mockito {
  "ContactService" should {
    "create contact" in {
      val inputReader = smartMock[InputReader]
      val contactService = new ContactsService(inputReader)
      inputReader.readLine("Enter name:") returns "Chris Harris"

      contactService.addContactsTo(Contacts.empty) mustEqual Contacts(Seq(Contact("Chris", Some("Harris"))))
    }

    "not throw exception if create contact fails" in {
      val inputReader = smartMock[InputReader]
      val contactService = new ContactsService(inputReader)
      inputReader.readLine("Enter name:") returns "Chris B Harris"

      contactService.addContactsTo(Contacts.empty) mustEqual Contacts.empty
    }

    "search contact for given name" in {
      val contact1 = Contact("Chris", Some("Blah"))
      val contact2 = Contact("Blah", Some("Harris"))
      val contacts = Contacts(Seq(contact1, contact2))

      val inputReader = smartMock[InputReader]
      val contactService = new ContactsService(inputReader)
      inputReader.readLine("Enter name:") returns "Chris"

      contactService.searchContacts(contacts) mustEqual Contacts(Seq(contact1))
    }
  }
}
