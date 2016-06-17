import org.junit.Test
import org.junit.Assert.*
import Color.*
import java.util.LinkedList

class RBTreeTest {
    @Test
    fun show() {
        val tree = RBTree<Int>()
        //add tests
        tree.add(50)
        assert(tree.contains(50))
        assert(!tree.contains(40)) {"very strange error"}
        tree.add(5)

        assertEquals(tree.getRootValue(), 50)

        tree.add(30)
        val it = tree.iterator()

        assertEquals(it.color(), RED)
        var value = it.next()
        assertEquals(value, 5)

        assertEquals(it.color(), BLACK)
        value = it.next()
        assertEquals(value, 30)

        assertEquals(it.color(), RED)
        value = it.next()
        assertEquals(value, 50)

        assertEquals(tree.getRootValue(), 30)

        tree.add(3)
        val iterator = tree.iterator()

        assertEquals(iterator.color(), RED)
        value = iterator.next()
        assertEquals(value, 3)

        assertEquals(iterator.color(), BLACK)
        value = iterator.next()
        assertEquals(value, 5)

        assertEquals(iterator.color(), BLACK)
        value = iterator.next()
        assertEquals(value, 30)

        assertEquals(iterator.color(), BLACK)
        value = iterator.next()
        assertEquals(value, 50)

        assertEquals(tree.getRootValue(), 30)

        tree.add(1)
        val iterarishe = tree.iterator()

        assertEquals(iterarishe.color(), RED)
        value = iterarishe.next()
        assertEquals(value, 1)

        assertEquals(iterarishe.color(), BLACK)
        value = iterarishe.next()
        assertEquals(value, 3)

        assertEquals(iterarishe.color(), RED)
        value = iterarishe.next()
        assertEquals(value, 5)

        assertEquals(iterarishe.color(), BLACK)
        value = iterarishe.next()
        assertEquals(value, 30)
        assertEquals(tree.getRootValue(), 30)

        assertEquals(iterarishe.color(), BLACK)
        value = iterarishe.next()
        assertEquals(value, 50)

        //remove tests
        tree.remove(50)
        val i = tree.iterator()

        assertEquals(i.color(), BLACK)
        value = i.next()
        assertEquals(value, 1)

        assertEquals(i.color(), BLACK)
        value = i.next()
        assertEquals(value, 3)
        assertEquals(tree.getRootValue(), 3)

        assertEquals(i.color(), RED)
        value = i.next()
        assertEquals(value, 5)

        assertEquals(i.color(), BLACK)
        value = i.next()
        assertEquals(value, 30)

        val rbtree = RBTree<Int>()
        rbtree.add(30)
        rbtree.add(12)
        rbtree.add(50)
        rbtree.add(4)
        rbtree.add(25)
        rbtree.add(34)
        rbtree.add(56)
        rbtree.add(1)
        rbtree.add(5)
        rbtree.add(20)
        rbtree.add(27)
        rbtree.add(32)
        rbtree.add(40)
        rbtree.add(52)
        rbtree.add(78)
        rbtree.add(6)
        rbtree.remove(6)
        rbtree.remove(32)
        rbtree.remove(40)
        rbtree.remove(52)
        rbtree.remove(78)
        rbtree.remove(50)

        val iter = rbtree.iterator()

        assertEquals(iter.color(), BLACK)
        value = iter.next()
        assertEquals(value, 1)

        assertEquals(iter.color(), BLACK)
        value = iter.next()
        assertEquals(value, 4)

        assertEquals(iter.color(), BLACK)
        value = iter.next()
        assertEquals(value, 5)

        assertEquals(iter.color(), BLACK)
        value = iter.next()
        assertEquals(value, 12)
        assertEquals(rbtree.getRootValue(), 12)

        assertEquals(iter.color(), RED)
        value = iter.next()
        assertEquals(value, 20)

        assertEquals(iter.color(), BLACK)
        value = iter.next()
        assertEquals(value, 25)

        assertEquals(iter.color(), RED)
        value = iter.next()
        assertEquals(value, 27)

        assertEquals(iter.color(), BLACK)
        value = iter.next()
        assertEquals(value, 30)

        assertEquals(iter.color(), RED)
        value = iter.next()
        assertEquals(value, 34)

        assertEquals(iter.color(), BLACK)
        value = iter.next()
        assertEquals(value, 56)

        assertEquals(iter.hasNext(), false)

        val list = LinkedList<Int>()
        list.add(1)
        list.add(4)
        list.add(5)
        list.add(12)
        list.add(20)
        list.add(25)
        list.add(27)
        list.add(30)
        list.add(34)
        list.add(56)

        assertEquals(rbtree.remove(333), false)
        assertEquals(rbtree.add(1), false)
        assertEquals(rbtree.add(4), false)

        assertArrayEquals(rbtree.toArray(), list.toArray())

        assertEquals(rbtree.containsAll(list), true)
        rbtree.removeAll(list)
        assertEquals(rbtree.isEmpty(), true)

        val rbTreeForRetain = RBTree<Int>()
        rbTreeForRetain.add(1)
        rbTreeForRetain.add(4)
        rbTreeForRetain.add(5)
        rbTreeForRetain.add(12)
        rbTreeForRetain.add(20)
        rbTreeForRetain.add(25)
        rbTreeForRetain.add(27)
        rbTreeForRetain.add(30)
        rbTreeForRetain.add(34)
        rbTreeForRetain.add(56)

        val list1 = LinkedList<Int>()
        list1.add(20)
        list1.add(27)
        list1.add(56)

        assertEquals(rbTreeForRetain.retainAll(list1), true)
        assertEquals(rbTreeForRetain.size, 3)
        assertEquals(rbTreeForRetain.contains(20), true)
        assertEquals(rbTreeForRetain.contains(27), true)
        assertEquals(rbTreeForRetain.contains(56), true)
    }
}
