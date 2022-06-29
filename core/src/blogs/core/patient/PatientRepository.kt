package blogs.core.patient

interface PatientRepository {
    fun getAll(): List<Patient>
    fun add(patient: Patient)
}
