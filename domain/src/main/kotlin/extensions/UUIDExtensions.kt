package extensions

import java.util.*

fun UUID.isEmpty(): Boolean{
    return this == UUID(0, 0);
}