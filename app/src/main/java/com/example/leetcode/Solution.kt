package com.example.leetcode

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.log2

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Node(var `val`: Int) {
    var children: List<Node?> = listOf()
}

fun main() {
    val raw = "[[5,10],[6,8],[1,5],[2,3],[1,10]]"
    raw.removeSurrounding("[[", "]]")
        .split("],[")
        .map { child ->
            child.split(",")
                .map { item ->
                    item.toInt()
                }.toIntArray()
        }.toTypedArray().let { data ->
            println(
                Solution().minGroups(data)
            )
        }

    val nodes1 = listOf<Int?>(1, 3, 2, 5)
    val nodes2 = listOf(3, 9, 20, null, null, 15, 7)
//    Solution().levelOrder(nodes2.toTree())
}


class Solution {

    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val width = matrix.first().size
        var head = 0
        var tail = matrix.size - 1
        var mid: Int
        while (head <= tail) {
            mid = head + (tail - head).div(2)
            if (target >= matrix[mid].first() && target < matrix[mid + 1].first()) break
            if (target < matrix[mid].first()) {
                tail = mid - 1
            } else {
                head = mid + 1
            }

        }

        return false
    }

    fun numberOfWays(startPos: Int, endPos: Int, k: Int): Int {
        val module = 1000000007
        val minStep = (endPos - startPos)
        if (minStep > k) return 0
        if (minStep.rem(2) != k.rem(2)) return 0
        var res = 1
        for (i in minStep..k) {

        }
        return res
    }

//    fun lengthOfLIS(nums: IntArray, k: Int): Int {
//        var res = 1
//        var count = 1
//        for (i in 1..nums.lastIndex) {
//            if (nums[i] > nu)
//        }
//    }

}


fun Array<CharArray>.travel(
    startX: Int,
    startY: Int,
    action: (Int, Int) -> Unit,
    takeIf: (Int, Int) -> Boolean = { _, _ -> true },
    visitedState: Array<BooleanArray> = Array(size) {
        BooleanArray(first().size) { false }
    },
    algo: String = "DFS"
) {
    val queue = ArrayDeque<Pair<Int, Int>>().apply {
        addFirst(startX to startY)
    }
    while (queue.isNotEmpty()) {
        val point = if (algo == "DFS") {
            queue.removeFirst()
        } else {
            queue.removeLast()
        }
        if (visitedState[point.first][point.second]) continue
        visitedState[point.first][point.second] = true
        action(point.first, point.second)
        moves.forEach { move ->
            val nextX = point.first + move.first
            val nextY = point.second + move.second
            if (isLegalMove(nextX, nextY)
                && !visitedState[nextX][nextY]
                && takeIf(nextX, nextY)
            ) {
                queue.addFirst(nextX to nextY)
            }
        }
    }
}

fun Array<IntArray>.travel(
    startX: Int,
    startY: Int,
    action: (Int, Int) -> Unit,
    takeIf: (Int, Int) -> Boolean = { _, _ -> true },
    visitedState: Array<BooleanArray> = Array(size) {
        BooleanArray(first().size) { false }
    },
    algo: String = "DFS"
) {
    val queue = ArrayDeque<Pair<Int, Int>>().apply {
        addFirst(startX to startY)
    }
    while (queue.isNotEmpty()) {
        val point = if (algo == "DFS") {
            queue.removeFirst()
        } else {
            queue.removeLast()
        }
        if (visitedState[point.first][point.second]) continue
        visitedState[point.first][point.second] = true
        action(point.first, point.second)
        moves.forEach { move ->
            val nextX = point.first + move.first
            val nextY = point.second + move.second
            if (isLegalMove(nextX, nextY)
                && !visitedState[nextX][nextY]
                && takeIf(nextX, nextY)
            ) {
                queue.addFirst(nextX to nextY)
            }
        }
    }
}

val moves = listOf(
    -1 to 0,
    0 to -1,
    1 to 0,
    0 to 1
)

fun Array<IntArray>.isLegalMove(x: Int, y: Int): Boolean {
    return x in indices && y in first().indices
}

fun Array<CharArray>.isLegalMove(x: Int, y: Int): Boolean {
    return x in indices && y in first().indices
}


fun <T> Collection<T>.countDistinctItems() = this.groupingBy { it }.eachCount()

inline fun Int.everyPair(action: (Int, Int) -> Unit) {
    for (i in 0 until this - 1) {
        for (j in i + 1 until this) {
            action(i, j)
        }
    }
}

fun midPoint(head: Int, tail: Int) = head + (tail - head).div(2)

fun Pair<Int, Int>.flattenIndex(width: Int) = first.times(width).plus(second)

fun Int.referAxis(width: Int) = this.div(width) to this.rem(width)

fun TreeNode.travel(
    action: (TreeNode?) -> Unit,
    algo: String = "DFS"
) {
    val queue = ArrayDeque<TreeNode?>()
    queue.addFirst(this)
    while (queue.isNotEmpty()) {
        val node = if (algo == "DFS") {
            queue.removeFirst()
        } else {
            queue.removeLast()
        }
        action(node)
        if (node?.left != null || node?.right != null) {
            queue.addFirst(node.left)
            queue.addFirst(node.right)
        }
    }
}

fun List<Int?>.toTree(): TreeNode {
    val nodes = map { value ->
        var node: TreeNode? = null
        value?.let {
            node = TreeNode(it)
        }
        node
    }
    nodes.forEachIndexed { index, node ->
        node?.left = nodes.getOrNull(2 * index + 1)
        node?.right = nodes.getOrNull(2 * index + 2)
    }
    return nodes.first()!!
}







































