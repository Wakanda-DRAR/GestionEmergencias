# Gestión Emergencias

## Hecho por Daniel Andrés Moreno Rey , Rodrigo Rojas Redondo , Arturo Lopez Castaño y Roberto Quilez


# Link al repositorio

https://github.com/Wakanda-DRAR/GestionEmergencias.git


# Video de instalación y muestra de un microservicio

https://www.youtube.com/watch?v=t7rg-MPO-gQ


## Descripción

El microservicio Gestión de Emergencias está diseñado para manejar y coordinar respuestas a diferentes tipos de emergencias (ambulancias, incendios y policía) de manera eficiente. Este sistema modular está construido sobre Spring Boot y permite la integración de datos, asignación de recursos, y resolución de incidentes en tiempo real. Cada módulo representa un tipo de emergencia, y está organizado en capas para separar la lógica de negocio, controladores y persistencia.

### Funcionalidades Generales
Gestión de emergencias específicas: Manejo de datos y seguimiento de emergencias relacionadas con servicios de ambulancias, bomberos y policía.
Resolución de incidentes: Registro y cierre de casos, vinculados a los recursos utilizados y las acciones realizadas.
Asignación de recursos: Coordinación automatizada de personal y equipamiento según las necesidades de cada emergencia.
Módulos Específicos

#### 1. Módulo de Ambulancias

Objetivo: Gestionar emergencias médicas y accidentes.
Entidades principales:
Ambulancia: Representa las unidades móviles disponibles.
Accidentado: Registra información de las personas involucradas.
Hospital: Centros médicos asociados para traslado de pacientes.
EmergenciaResueltaAmbulancia: Historias de emergencias resueltas.
Servicios:
Asignación automática de ambulancias a emergencias.
Derivación de pacientes a hospitales.
Registro y seguimiento de emergencias médicas.

#### 2. Módulo de Incendios

Objetivo: Coordinar la respuesta ante incendios reportados.
Entidades principales:
Bombero: Información de los equipos de bomberos disponibles.
Incendio: Datos relacionados con los incendios registrados.
EmergenciaResuelta: Detalles de incendios controlados.
Servicios:
Despliegue de bomberos al lugar del incendio.
Registro de las acciones tomadas durante la emergencia.
Resolución y reporte de incidentes.

#### 3. Módulo de Policía

Objetivo: Manejar emergencias relacionadas con la seguridad pública.
Entidades principales:
Carcel: Centros de detención asignados.
Delito: Registro de delitos reportados.
Policia: Agentes disponibles para intervención.
EmergenciaResueltaPolicia: Registro de emergencias policiales solucionadas.
Servicios:
Asignación de agentes para resolver casos.
Gestión de datos sobre delitos y sospechosos.
Resolución y cierre de incidentes, incluyendo traslado de detenidos.

### Arquitectura

El microservicio sigue una arquitectura basada en capas:

Controladores: Exponen endpoints REST para interacción con los usuarios.
Servicios: Contienen la lógica de negocio específica de cada módulo.
Repositorios: Manejan la persistencia de datos mediante JPA.
Tecnologías Utilizadas
Backend: Java 17, Spring Boot.
Base de datos: MySQL.
Contenedores: Docker (opcional para despliegue).
Casos de Uso
Reportar un accidente y asignar una ambulancia al lugar.
Coordinar el envío de bomberos ante un incendio reportado.
Asignar agentes de policía para investigar un delito y manejar detenidos.
