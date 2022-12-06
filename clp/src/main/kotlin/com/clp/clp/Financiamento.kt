class Financiamento (
    val usuario: String,
    val valorFinanciamento: Double,
    val qntParcelas: Int,
    val vendedor: Vendedor,
    val item: ItemFinanciado
        ){

    fun getAll(): List<Any>{
        val listaTodos = listOf(usuario, valorFinanciamento,qntParcelas,vendedor,item)
        return listaTodos
    }
}