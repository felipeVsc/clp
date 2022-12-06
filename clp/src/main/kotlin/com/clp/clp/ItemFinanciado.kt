class ItemFinanciado(
    val tipoItem: String,
    val valorItem: Double,
    val docItem: String,
) {
    fun getAll(): List<Any>{
        val listaTodos = listOf(tipoItem,valorItem,docItem)
        return listaTodos
    }

}