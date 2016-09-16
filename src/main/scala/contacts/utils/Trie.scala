package contacts.utils

import scala.collection.JavaConverters._

case class Trie(char : Option[Char] = None, word: String, var isLeaf: Boolean) {
  var children: scala.collection.mutable.Map[Char, Trie] = new java.util.TreeMap[Char, Trie]().asScala

  def append(key: String): Unit = {
    var (node, currentIndex) = findNodeAndIndex(this, key, 0)

    if(currentIndex == key.length -1)
      node.isLeaf = true

    while(currentIndex < key.length) {
      val currentChar = key.charAt(currentIndex)
      val isLeaf = if(currentIndex == key.length - 1) true else false
      val newNode = new Trie(Some(currentChar), key.substring(0, currentIndex + 1), isLeaf)
      node.children.put(currentChar, newNode)
      node = newNode
      currentIndex = currentIndex + 1
    }
  }

  def findByPrefix(prefix: String): Seq[String] = {
    val (node, _) = findNodeAndIndex(this, prefix, 0)

    if(node != Trie.root)
      getAll(node, Seq.empty).distinct.map(_.word)
    else
      Seq.empty
  }

  private def findNodeAndIndex(node: Trie, key: String, currentIndex: Int): (Trie, Int) = {
    val currentChar = key.charAt(currentIndex)

    node.children.get(currentChar) match {
      case Some(trie) if key.length - 1 != currentIndex => findNodeAndIndex(trie, key, currentIndex + 1)
      case Some(trie)  => (trie, currentIndex)
      case None  => (node, currentIndex)
    }
  }

  private def getAll(node: Trie, nodes: Seq[Trie]): Seq[Trie] = {
    val newNodeList = if(node.isLeaf)  nodes :+ node else nodes

    if (node.children.isEmpty)
      newNodeList
    else
      node.children.values.toSeq.flatMap { child =>
        getAll(child, newNodeList)
      }
  }
}

object Trie {
  val root = Trie(None, "", false)
}

