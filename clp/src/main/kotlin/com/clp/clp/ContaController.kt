package com.clp.clp

import DBConnection
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("financiamento/")
class ContaController {

    @GetMapping("vendedores")
    fun getVendedores(): ResponseEntity<MutableList<List<String>>> {
        val db = DBConnection()
        val conex = db.connectToDb()
        conex
        return ResponseEntity(db.getVendedor("select * from vendedor_table"), HttpStatus.OK)
    }
    @GetMapping("items")
    fun getItems(): ResponseEntity<MutableList<List<Any>>> {
        val db = DBConnection()
        val conex = db.connectToDb()
        conex
        return ResponseEntity(db.getItems("select * from itemsfinanciado_table"), HttpStatus.OK)
    }
    @GetMapping("financiamentos")
    fun getFinanciamentos(): ResponseEntity<MutableList<List<Any>>> {
        val db = DBConnection()
        val conex = db.connectToDb()
        conex
        return ResponseEntity(db.getFinanciamentos("select * from financiamento_table"), HttpStatus.OK)
    }

    // Get individuais (pra um unico financiamento/vendedor e etc)

    @GetMapping("vendedores/{docvendedor}")
    fun getVendedoresEspecifico(@PathVariable docvendedor: String): ResponseEntity<MutableList<List<String>>> {
        val db = DBConnection()
        val conex = db.connectToDb()
        conex
        return ResponseEntity(db.getVendedor("select * from vendedor_table where vendedordoc == {$docvendedor}"), HttpStatus.OK)
    }

    @GetMapping("items/{docitem}")
    fun getItemEspecifico(@PathVariable docitem: String): ResponseEntity<MutableList<List<Any>>> {
        val db = DBConnection()
        val conex = db.connectToDb()
        conex
        return ResponseEntity(db.getItems("select * from itemsfinanciado_table where docitem == {$docitem}"), HttpStatus.OK)
    }

    @GetMapping("financiamentos/{idfinanciamento}")
    fun getFinanciamentoEspecifico(@PathVariable idfinanciamento: String): ResponseEntity<MutableList<List<Any>>> {
        val db = DBConnection()
        val conex = db.connectToDb()
        conex
        return ResponseEntity(db.getFinanciamentos("select * from financiamento_table where idfinanciamento == {$idfinanciamento}"), HttpStatus.OK)
    }

    @GetMapping("financiamentos/{usuarioconta}")
    fun getFinanciamentoEspecificoConta(@PathVariable usuarioconta: String): ResponseEntity<MutableList<List<Any>>> {
        val db = DBConnection()
        val conex = db.connectToDb()
        conex
        return ResponseEntity(db.getFinanciamentos("select * from financiamento_table where usuarioconta == {$usuarioconta}"), HttpStatus.OK)
    }

// TODO Gerar os selects correspondentes! com as funcoes



}