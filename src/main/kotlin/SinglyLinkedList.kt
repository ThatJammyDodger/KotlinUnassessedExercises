package collections

class SinglyLinkedList<T> : ImperialMutableList<T> {
    private class Node<T>(var element: T,
                          var next: Node<T>? = null)

    override var size: Int = 0
        private set
    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    override fun add(element: T) {
        size++
        val newNode = Node(element, null)
        if (head == null) {
            head = newNode
            tail = newNode
            return
        }
        tail!!.next = newNode
        tail = newNode
    }

    override fun add(index:Int, element: T) {
        if (index !in 0..<size)
            throw IndexOutOfBoundsException()
        else if (index == size)
            add(element)

        size++
        val (previous, current) = traverseTo(index)
        if (previous == null)
            head = Node(element, current)
        else
            previous.next = Node(element, current)
    }

    override fun removeAt(index: Int): T {
        if (index !in 0..<size)
            throw IndexOutOfBoundsException()
        val (previous, current) = traverseTo(index)
        val result = current.element
        unlink(previous, current)
        return result
    }

    private fun traverseTo(index: Int):
            Pair<Node<T>?, Node<T>> {
        var previous: Node<T>? = null
        var current: Node<T>? = head
        for (i in 0..<index) {
            previous = current
            current = current!!.next
        }
        return Pair(previous, current!!)
    }
    private fun unlink(previous: Node<T>?, current: Node<T>) {
        if (previous == null)
            head = current.next
        else
            previous.next = current.next
        if (current == tail)
            tail = previous
        size--
    }

    override fun clear() {
        size = 0
        head = null
        tail = null
    }

    override fun get(index: Int): T {
        if (index !in 0..<size)
            throw IndexOutOfBoundsException()
        return traverseTo(index).second.element
    }

    override fun set(index: Int, element: T) {
        if (index !in 0..<size)
            throw IndexOutOfBoundsException()
        val current = traverseTo(index).second
        current.element = element
    }

    private fun find(element: T): Int {
        var current = head
        for (i in 0..<size) {
            if (current == null)
                return -1
            else if (current.element == element)
                return i
            current = current.next
        }
        return -1
    }

    override fun contains(element: T): Boolean = find(element) != -1

    override fun remove(element: T): Boolean {
        val position = find(element)
        if (position == -1)
            return false
        else {
            removeAt(position)
            return true
        }
    }

    override fun toString(): String {
        val sb = StringBuilder("[")
        var current: Node<T>? = head
        while (current != null) {
            sb.append(current.element.toString())
            current = current.next
        }
        sb.append("]")
        return sb.toString()
    }

    // iterator using an anonymous object
    override fun iterator(): Iterator<T> = object : Iterator<T> {
        private var nextNode: Node<T>? = head
        override fun hasNext(): Boolean = nextNode != null
        override fun next(): T {
            if (!hasNext())
                throw NoSuchElementException()
            val result: T = nextNode!!.element
            nextNode = nextNode!!.next
            return result
        }
    }

    // iterator using an inner class
//    private inner class SinglyLinkedListIterator : Iterator<T> {
//        private var nextNode: Node<T>? = head
//        override fun hasNext(): Boolean = nextNode != null
//        override fun next(): T {
//            if (!hasNext())
//                throw NoSuchElementException()
//            val result: T = nextNode!!.element
//            nextNode = nextNode!!.next
//            return result
//        }
//    }
//    override fun iterator(): Iterator<T> = this.SinglyLinkedListIterator()

    // iterator using a nested class
//    private class SinglyLinkedListIterator<T>(
//        targetList: SinglyLinkedList<T>
//    ) : Iterator<T> {
//        private var nextNode: Node<T>? = targetList.head
//        override fun hasNext(): Boolean = nextNode != null
//        override fun next(): T {
//            if (!hasNext())
//                throw NoSuchElementException()
//            val result: T = nextNode!!.element
//            nextNode = nextNode!!.next
//            return result
//        }
//    }
//    override fun iterator(): Iterator<T> = SinglyLinkedListIterator(this)

    override fun addAll(index: Int, other: ImperialMutableList<T>) {
        if (index !in 0..size)
            throw IndexOutOfBoundsException()
        if (other.size == 0)
            return
        if (index == 0) {
            val newHead: Node<T>? = null
            var tail: Node<T>? = newHead
            for (element in other){
                tail = Node(element, null)
                tail = tail.next
            }
            tail!!.next = head
            head = newHead
        } else {
            var (previous, current) = traverseTo(index - 1)
            val oldTail = current
            for (element in other) {
                previous!!.next = Node(element, null)
                previous = previous.next
            }
            previous!!.next = oldTail
        }
    }
}

// defining iterator using a separate class in this file, but
// outside `SinglyLinkedList` is not possible due to the access
// modifiers used on `head` and `Node<T>`