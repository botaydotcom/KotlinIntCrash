package kotlincrash

object CrashTest {
    val objString = Observable<String>()
    val objInt = Observable<Int>()
    val objSubInt = SubclassObservable<Int>()

    inline fun <T> Observable<T>.registerInline(
        crossinline onChanged: (T) -> Unit) {
        register { t -> onChanged(t) }
    }

    fun startNoCrash() {
        objString.register { t -> println("Value $t") }
        objSubInt.register { t -> println("Value $t") }
        objInt.register { t -> println("Value $t") }
    
        objString.registerInline { t -> println("Value $t") }
        objSubInt.registerInline { t -> println("Value $t") }        
    }

    fun startCrash() {
        objInt.registerInline { t -> println("Value $t") }
    }
}

fun main(args: Array<String>) {
    CrashTest.startNoCrash()
    CrashTest.startCrash()
}