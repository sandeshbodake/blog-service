package blogs.core.patient

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class PatientServiceTest : StringSpec() {

    private val repository = mockk<PatientRepository>(relaxed = true)
    private val service = PatientService(repository)

    init {
        "Initial Test" {
            every { repository.getAll() } returns listOf(patient())
            service.getAll() shouldBe listOf(patient())
            verify { repository.getAll() }
        }
    }
}
