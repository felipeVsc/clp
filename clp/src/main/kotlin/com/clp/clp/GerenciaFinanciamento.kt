package com.clp.clp

import DBConnection
import Financiamento
import ItemFinanciado
import Vendedor

class GerenciaFinanciamento(
    val conexao: DBConnection
) {

    fun gerarFinanciamento(item: ItemFinanciado, vendedor: Vendedor, usuario:String): Financiamento {
        // input + pegar instancia do item + vendedor para gerar o financiamenot

        // quando gerar o financiamento, gerar a tabela das parcelas tambem!
        val taxaDeJuros = 0.05
        val valor = item.valorItem * taxaDeJuros
        val financiamento = Financiamento(usuario, valor, 20, vendedor, item)
        val listaFinanciamento = financiamento.getAll()
        val idit = this.conexao.makeQuery("select max(iditem) from itemsfinanciado_table")
        this.conexao.makeQuery("INSERT INTO financiamento_table(usuarioconta,valorfinanciamento,qntparcela,vendedordoc,iditem) VALUES ('${listaFinanciamento[0]}','${listaFinanciamento[0]}','${listaFinanciamento[0]}')")
        return financiamento
    }

    fun gerarItem(): ItemFinanciado {
        // inputs com os item e gerando a instancia
        val item = ItemFinanciado("carro", 10000.0, "231231")
        // colocar aqui no negocio
        val listaItem = item.getAll()
        println(listaItem)
        this.conexao.makeQuery("INSERT INTO itemsfinanciado_table(tipoitem,valoritem,docitem) VALUES ('${listaItem[0]}','${listaItem[1]}','${listaItem[2]}')")
        return item
    }

    fun criarVendedor(): Vendedor {
        // inputs com o vendedor
        val vendedor = Vendedor("12345", "AutomoveisPecasLTDA")
        val listaVendedor = vendedor.getAll()
        this.conexao.makeQuery("INSERT INTO vendedor_table(vendedordoc,nomevendedor) VALUES ('${listaVendedor[0]}','${listaVendedor[1]}')")
        return vendedor
    }

}