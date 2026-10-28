package com.ejemplo.recordatorios.domain

import androidx.compose.ui.graphics.Color

object RepositorioRecordatorios {

    val recordatoriosSimulados: List<Recordatorio> = listOf(
        Recordatorio(
            id = 1L,
            titulo = "Inspección de Matafuegos",
            descripcion = "Control de carga y precintos en depósito principal",
            categoria = "Seguridad",
            prioridad = Prioridad.ALTA,
            estado = EstadoRecordatorio.EnCurso,
            fechaLimite = "25/10/2026"
        ),
        Recordatorio(
            id = 2L,
            titulo = "Revisión Tablero Eléctrico",
            descripcion = null,
            categoria = "Mantenimiento",
            prioridad = Prioridad.URGENTE,
            estado = EstadoRecordatorio.Pendiente,
            fechaLimite = "22/10/2026"
        ),
        Recordatorio(
            id = 3L,
            titulo = "Inventario de Repuestos",
            descripcion = "Cotejar insumos físicos con la planilla central",
            categoria = "Logística",
            prioridad = Prioridad.MEDIA,
            estado = EstadoRecordatorio.Completado("20/10/2026"),
            fechaLimite = "20/10/2026"
        ),
        Recordatorio(
            id = 4L,
            titulo = "Actualizar Protocolos",
            descripcion = "Subir los manuales actualizados al repositorio",
            categoria = "Seguridad",
            prioridad = Prioridad.BAJA,
            estado = EstadoRecordatorio.Pendiente,
            fechaLimite = null
        )
    )

    val categoriasUnicas: Set<String> = recordatoriosSimulados.map { it.categoria }.toSet()

    val coloresPorCategoria: Map<String, Color> = mapOf(
        "Seguridad" to Color(0xFFD32F2F),       // Rojo
        "Mantenimiento" to Color(0xFFF57C00),   // Naranja
        "Logística" to Color(0xFF1976D2),       // Azul
        "General" to Color(0xFF616161)          // Gris por defecto
    )

    fun filtrarPorPrioridad(prioridad: Prioridad): List<Recordatorio> {
        return recordatoriosSimulados.filter { it.prioridad == prioridad }
    }

    fun obtenerPendientes(): List<Recordatorio> {
        return recordatoriosSimulados.filter { it.estado is EstadoRecordatorio.Pendiente }
    }
}