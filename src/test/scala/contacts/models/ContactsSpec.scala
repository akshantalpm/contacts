package contacts.models

import org.specs2.mutable.Specification

class ContactsSpec extends Specification {

  "Contacts" should {
    "return empty contact list" in {
      Contacts.empty.contacts mustEqual Seq.empty
    }

    "add contact" in {
      val contact1 = Contact("Chris", Some("Harris"))
      Contacts.empty.addContact(contact1) mustEqual Contacts(Seq(contact1))
    }

    "search contacts" in {
      "by firstName" in {
        val contact1 = Contact("Chris", Some("Harris"))
        val contact2 = Contact("Blah", Some("Harris"))
        val contacts = Contacts(Seq(contact1, contact2))
        contacts.searchContact("Chris") mustEqual Contacts(Seq(contact1))
      }

      "by lastName" in {
        val contact1 = Contact("Chris", Some("Blah"))
        val contact2 = Contact("Blah", Some("Harris"))
        val contacts = Contacts(Seq(contact1, contact2))
        contacts.searchContact("Harris") mustEqual Contacts(Seq(contact2))
      }

      "return search contact in appropriate order" in {
        val contact1 = Contact("Chris", Some("Blah"))
        val contact2 = Contact("Chris", None)
        val contacts = Contacts(Seq(contact1, contact2))
        contacts.searchContact("Chris") mustEqual Contacts(Seq(contact1, contact2))
      }

      "return empty list if criteria is not matched" in {
        val contact1 = Contact("Chris", Some("Blah"))
        val contact2 = Contact("Chris", None)
        val contacts = Contacts(Seq(contact1, contact2))
        contacts.searchContact("abcd") mustEqual Contacts.empty
      }
    }
  }
}
