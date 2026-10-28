package com.ejemplo.recordatorios.domain

sealed class EstadoRecordatorio {
    data object Pendiente : EstadoRecordatorio()
    data object EnCurso : EstadoRecordatorio()
    data class Completado(val fechaFinalizacion: String) : EstadoRecordatorio()
}