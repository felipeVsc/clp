
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

class DBConnection {
    var username = "postgres"
    var password = "admin"
    lateinit var connection: Connection
    fun connectToDb(){
        val jdbcUrl = "jdbc:postgresql://localhost:5432/clp"
        this.connection = DriverManager.getConnection(jdbcUrl, this.username, this.password)

    }

    fun makeQuery(query: String): ResultSet? {
        val queryCall = this.connection.prepareStatement(query)
        return queryCall.executeQuery()

    }
    fun makeQueryNoReturn(query: String): Boolean {
        val queryCall = this.connection.prepareStatement(query)
        return queryCall.execute()

    }


    fun getVendedor(query: String): MutableList<List<String>> {
        val res = makeQuery(query)
        val listaVendedor = mutableListOf<List<String>>()
        if (res != null) {
            while(res.next()){
                val novalista = listOf(res.getString("vendedordoc"),res.getString(("nomevendedor")))
                listaVendedor.add(novalista)
            }
        }
        return listaVendedor
    }

    fun getItems(query: String): MutableList<List<Any>> {

        val res = makeQuery(query)
        var listaItems = mutableListOf<List<Any>>()
        if (res != null) {
            while(res.next()){

                val novalista = listOf(res.getInt("iditem"),res.getString("tipoitem"),res.getDouble("valoritem"),res.getString(("docitem")))
                listaItems.add(novalista)
            }
        }

        return listaItems
    }

    fun getFinanciamentos(query: String): MutableList<List<Any>> {

        val res = makeQuery(query)
        var listaItems = mutableListOf<List<Any>>()
        if (res != null) {
            while(res.next()){

                val novalista = listOf(res.getInt("idfinanciamento"),res.getString("usuarioconta"),res.getDouble("valorfinanciamento"),res.getInt("qntparcela"),res.getString("vendedordoc"),res.getInt("iditem"))
                listaItems.add(novalista)
            }
        }
        println(listaItems)
        return listaItems
    }
    // cadastrar RETORNAR O PK

    fun createVendedor(docvendedor : String, nomevendedor : String): String {
        makeQueryNoReturn("INSERT INTO vendedor_table (vendedordoc,nomevendedor) VALUES ('$docvendedor','$nomevendedor')")
        return "ok"
    }

    fun createItem(tipoitem : String, valoritem : Double, docitem : String): String {
        makeQueryNoReturn("REPLACE INTO itemsfinanciado_table(tipoitem,valoritem,docitem) VALUES ('$tipoitem', '$valoritem', '$docitem')")
        return "ok"
    }

    fun createFinanciamento(usuario : String, iditem : Int, qntparcela : String, vendedordoc : String): String {
        val valoritemquery = makeQuery("select valoritem from itemsfinanciado_table where iditem=$iditem")
        // pegar a resp
        var valoritem = 0.0
        if (valoritemquery != null) {
            while(valoritemquery.next()){

                valoritem = valoritemquery.getDouble("valoritem")
            }
        }
        makeQueryNoReturn("INSERT INTO financiamento_table(usuarioconta,valorfinanciamento,qntparcela,vendedordoc,iditem) values ('$usuario','$valoritem','$qntparcela','$vendedordoc','$iditem') ")
        generateParcelas(qntparcela,iditem)
        return "ok"
    }

    fun generateParcelas(qntparcela: String, iditem: Int){
        val qntparcelaint = qntparcela.toInt()
        for (x in 1..qntparcelaint){
            makeQueryNoReturn("INSERT INTO pagamentos(iditem, parcela, pago) values ('$iditem','$x','n') ")
        }
    }

    fun deleteVendedor(docvendedor : String): String {
        makeQueryNoReturn("DELETE FROM vendedor_table WHERE vendedordoc = '$docvendedor'")
        return "ok"
    }

    fun deleteItem(docitem : String): String {
        makeQueryNoReturn("DELETE FROM itemsfinanciado_table WHERE docitem = '$docitem'")
        return "ok"
    }

    fun deleteFinanciamento(idfinan : Int): String {
        makeQueryNoReturn("DELETE FROM financiamento_table WHERE id = '$idfinan'")
        return "ok"
    }

    fun pagarParcela(idfinan: Int, parcela: Int): String {
        val iditem = makeQuery("SELECT iditem from financiamento_table WHERE idfinanciamento = $idfinan");
        var item = 0;
        if (iditem != null) {
            while(iditem.next()){

                item = iditem.getInt("iditem");
            }
        }
        makeQueryNoReturn("UPDATE pagamentos SET pago = 's' WHERE iditem = $item AND parcela = $parcela");
        return "Ok"

    }


}