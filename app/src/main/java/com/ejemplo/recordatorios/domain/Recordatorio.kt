package com.ejemplo.recordatorios.domain

data class Recordatorio(
    val id: Long,
    val titulo: String,
    val descripcion: String? = null,
    val categoria: String,
    val prioridad: Prioridad,
    val estado: EstadoRecordatorio = EstadoRecordatorio.Pendiente,
    val fechaLimite: String? = null
)