package blogs

import io.micronaut.runtime.Micronaut

object ApiApplication {
    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build(*args).start()
    }
}
