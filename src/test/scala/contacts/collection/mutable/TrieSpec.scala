package contacts.collection.mutable

import org.specs2.mutable.Specification

class TrieSpec extends Specification {
  "Trie" should {
    "insert data" in {
      val rootNode = Trie(None, "", false)
      rootNode.append("the")

      rootNode.children('t') mustEqual Trie(Some('t'), "t", false)
      rootNode.children('t').children('h') mustEqual Trie(Some('h'), "th", false)
      rootNode.children('t').children('h').children('e') mustEqual Trie(Some('e'), "the", true)
    }

    "search data" in {
      val rootNode = Trie(None, "", false)
      rootNode.append("Chris")
      rootNode.append("Chris Harris")

      rootNode.findByPrefix("Chris") mustEqual Seq("Chris", "Chris Harris")
    }

    "return search results by ranking exact matches higher than other matches" in {
      val rootNode = Trie(None, "", false)
      rootNode.append("Chris Harris")
      rootNode.append("Chris")

      rootNode.findByPrefix("Chris") mustEqual Seq("Chris", "Chris Harris")
    }
  }
}
