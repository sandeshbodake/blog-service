package blogs

import utility.Response

interface BlogController {
    fun getAll(): Response<Any>
}
