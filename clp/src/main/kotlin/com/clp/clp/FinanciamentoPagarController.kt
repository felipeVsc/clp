package com.clp.clp

import DBConnection

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("financiamento/pagar/")
class FinanciamentoPagarController {
     @PutMapping("{idfinanciamento}&{parcela}")
    fun pagarParcela(@PathVariable idfinanciamento: Int, @PathVariable parcela : Int): ResponseEntity<String> {
        val db = DBConnection()
        db.connectToDb()
        return ResponseEntity(db.pagarParcela(idfinanciamento,parcela), HttpStatus.OK)
    }

}