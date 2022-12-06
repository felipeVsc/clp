package com.clp.clp

import DBConnection

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("financiamento/cadastrar/")
class FinanciamentoCadastroController {

    // Vendedores
    @PutMapping("vendedores/{docvendedor}&{nomevendedor}")
    fun postVendedores(@PathVariable docvendedor: String = "", @PathVariable nomevendedor: String  = ""): ResponseEntity<String> {
        val db = DBConnection()
        db.connectToDb()

        return ResponseEntity(db.createVendedor(docvendedor,nomevendedor), HttpStatus.OK)
    }

    // Item

    @PutMapping("items/{tipoitem}&{valoritem}&{docitem}")
    fun postItem(@PathVariable tipoitem: String = "",@PathVariable valoritem: Double = -1.0,
                 @PathVariable docitem: String = ""
    ): ResponseEntity<String> {
        val db = DBConnection()
        db.connectToDb()

        return ResponseEntity(db.createItem(tipoitem,valoritem,docitem), HttpStatus.OK)
    }

    // Financiamento

    @PutMapping("financiamento/{usuario}&{iditem}&{qntparcela}&{vendedordoc}")
    fun postFinanciamento(@PathVariable usuario: String = "", @PathVariable iditem: Int = -1, @PathVariable qntparcela: String = "", @PathVariable vendedordoc : String = ""
    ): ResponseEntity<String> {
        val db = DBConnection()
        db.connectToDb()
        println("oi")
        return ResponseEntity(db.createFinanciamento(usuario, iditem,qntparcela,vendedordoc), HttpStatus.OK)
    }

    // cadastrar item, cadastrar financiamento, cadastrar vendedor
}