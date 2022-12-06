package com.clp.clp

import DBConnection

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("financiamento/apagar/")
class FinanciamentoApagarController {
    // Vendedores
    @DeleteMapping("vendedores/{docvendedor}")
    fun postVendedores(@PathVariable docvendedor: String): ResponseEntity<String> {
        val db = DBConnection()
        db.connectToDb()
        return ResponseEntity(db.deleteVendedor(docvendedor), HttpStatus.OK)
    }
    // Item
    @DeleteMapping("items/{docitem}")
    fun postItem(@PathVariable docitem: String,
    ): ResponseEntity<String> {
        val db = DBConnection()
        db.connectToDb()
        return ResponseEntity(db.deleteItem(docitem), HttpStatus.OK)
    }
    // Financiamento
    @DeleteMapping("financiamento/{idfinanc}")
    fun postFinanciamento(@PathVariable idfinanc: Int): ResponseEntity<String> {
        val db = DBConnection()
        db.connectToDb()
        return ResponseEntity(db.deleteFinanciamento(idfinanc), HttpStatus.OK)
    }


}