class Vendedor(
    val docVendedor: String,
    val nomeVendedor: String
) {

    fun getAll(): List<Any>{
        val listaTodos = listOf(docVendedor,nomeVendedor)
        return listaTodos
    }
}