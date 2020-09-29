package kotlincrash

object CrashTest {
    val objString = Observable<String>()
    val objInt = Observable<Int>()
    val objSubInt = JavaSubclassObservable<Int>()
    val objKotlinSubInt = KotlinSubclassObservable<Int>()

    inline fun <T> Observable<T>.registerInline(
        crossinline onChanged: (T) -> Unit) {
        register { t -> onChanged(t) }
    }

    fun startNoCrash() {
        objString.register { t -> println("objString.register => Value $t") }
        objSubInt.register { t -> println("objSubInt.register => Value $t") }
        objInt.register { t -> println("objInt.register => Value $t") }
        objKotlinSubInt.register { t -> println("objKotlinSubInt.register => Value $t") }
    
        objString.registerInline { t -> println("objString.registerInline => Value $t") }
        objSubInt.registerInline { t -> println("objSubInt.registerInline => Value $t") }
    }

    fun startCrash() {
        objInt.registerInline { t -> println("objInt.registerInline => Value $t") }
        objKotlinSubInt.registerInline { t -> println("objKotlinSubInt.registerInline => Value $t") }
    }
}

fun main(args: Array<String>) {
    CrashTest.startNoCrash()
    CrashTest.startCrash()
}