package blogs.core.patient

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PatientService(@Inject private val patientRepository: PatientRepository) {
    fun getAll(): List<Patient> = patientRepository.getAll()

    fun add(patient: Patient) = patientRepository.add(patient)
}
