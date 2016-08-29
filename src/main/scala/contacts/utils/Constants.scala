package contacts.utils

object Constants {
  val DisplayActions = "1) Add Contact 2) Search 3) Exit"
  val ExitMessage = "Happy Searching"
  val PromptName = "Enter name:"
  val ZeroRecords = "No records found"
  val InvalidOption = "Not a valid option. Please try again"
  val InvalidContactFormat = "Could not create contact. Please try again with 'firstName lastName' format"
  val ContactLengthLimitExceeded = s"Contact length exceeded. Limit is ${AppConfig.ContactLengthLimit}"
}
