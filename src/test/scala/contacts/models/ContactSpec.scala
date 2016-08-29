package contacts.models

import contacts.utils.Constants
import org.specs2.mutable.Specification

class ContactSpec extends Specification {
  "Contact" should {
    "create contact with firstName and lastName" in {
      val contact = Contact.create("  Chris       Harris       ")
      contact.firstName mustEqual "Chris"
      contact.lastName mustEqual Some("Harris")
    }

    "create contact with only firstName" in {
      val contact = Contact.create("Chris     ")
      contact.firstName mustEqual "Chris"
      contact.lastName mustEqual None
    }

    "throw an exception for contact format with middle name" in {
      Contact.create("Chris Blah Haris") should throwA(new RuntimeException(Constants.InvalidContactFormat))
    }

    "throw an exception if contact length limit exceeds" in {
      val contactName = "bdhbfjewlfrekrewkrjlj ewrlkerlkwejrlrjlewkjrklelwjrlklkw"
      Contact.create(contactName) should throwA(new RuntimeException(Constants.ContactLengthLimitExceeded))
    }

    "return contact if search criteria is satisfied in first name" in {
      Contact("Chris", None).isMatch("chris") must beTrue
    }

    "return contact if search criteria is satisfied in last name" in {
      Contact("Chris", Some("Harris")).isMatch("harris") must beTrue
    }

    "not return contact if search criteria is not satisfied in first name" in {
      Contact("Chris", None).isMatch("harris") must beFalse
    }

    "not return contact if search criteria is not satisfied" in {
      Contact("Chris", Some("Harris")).isMatch("random") must beFalse
    }
  }
}
