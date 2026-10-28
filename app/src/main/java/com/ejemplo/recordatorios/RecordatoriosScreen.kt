package com.ejemplo.recordatorios.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ejemplo.recordatorios.domain.EstadoRecordatorio
import com.ejemplo.recordatorios.domain.Recordatorio
import com.ejemplo.recordatorios.domain.RepositorioRecordatorios

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordatoriosScreen(
    recordatorios: List<Recordatorio> = RepositorioRecordatorios.recordatoriosSimulados,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recordatorios Contextuales", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(recordatorios, key = { it.id }) { recordatorio ->
                RecordatorioCard(recordatorio = recordatorio)
            }
        }
    }
}

@Composable
fun RecordatorioCard(
    recordatorio: Recordatorio,
    modifier: Modifier = Modifier
) {
    val colorCategoria = RepositorioRecordatorios.coloresPorCategoria[recordatorio.categoria]
        ?: Color.Gray

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = colorCategoria.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = recordatorio.categoria,
                        color = colorCategoria,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Text(
                    text = "Prioridad: ${recordatorio.prioridad.name}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = recordatorio.titulo,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = recordatorio.descripcion ?: "Sin descripción adicional",
                style = MaterialTheme.typography.bodyMedium,
                color = if (recordatorio.descripcion != null)
                    MaterialTheme.colorScheme.onSurfaceVariant
                else
                    MaterialTheme.colorScheme.outline
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val (textoEstado, colorEstado) = when (recordatorio.estado) {
                    is EstadoRecordatorio.Pendiente -> "Pendiente" to Color(0xFFD32F2F)
                    is EstadoRecordatorio.EnCurso -> "En Curso" to Color(0xFFF57C00)
                    is EstadoRecordatorio.Completado ->
                        "Completado (${recordatorio.estado.fechaFinalizacion})" to Color(0xFF388E3C)
                }

                Text(
                    text = "Estado: $textoEstado",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = colorEstado
                )

                recordatorio.fechaLimite?.let { fecha ->
                    Text(
                        text = "Vence: $fecha",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecordatoriosScreenPreview() {
    MaterialTheme {
        RecordatoriosScreen()
    }
}