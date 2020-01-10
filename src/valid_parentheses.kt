fun main() {
    val s = "()[]{}"
    println("answer:" + valid_parentheses(s))
}

/**
 * 172 ms,63.64%
 * 31.4 MB, 100%
 */
fun valid_parentheses(s: String): Boolean {
    if (s.isEmpty())
        return true
    if (s.length % 2 == 1)
        return false

    val hashmap = hashMapOf<Char, Char>()
    hashmap.put(')', '(')
    hashmap.put(']', '[')
    hashmap.put('}', '{')

    var list = mutableListOf<Char>()

    for (c in s) {
        if (hashmap.containsKey(c)) {
            if (!list.isEmpty()) {
                var v = list.removeAt(list.size - 1)
                if (!v.equals(hashmap.get(c)))
                    return false;
            } else return false;
        } else list.add(c)
    }

    return list.isEmpty()
}