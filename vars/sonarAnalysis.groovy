@NonCPS
def call(Boolean abortPipeline) {
    abortPipeline = abortPipeline ?: false // Asigna false si abortPipeline es nulo
    
    try {
        timeout(time: 5, unit: 'MINUTES') {
            echo "Ejecución de las pruebas de calidad de código"
            sleep 30 // Simulación de tiempo de espera
            
            // Simular resultado exitoso
            def sonarQubeResult = "SUCCESS"
            
            if (abortPipeline && sonarQubeResult != "SUCCESS") {
                error "QualityGate de SonarQube no pasó. Abortando el pipeline."
            }
        }
    } catch (Exception e) {
        error "Error en el escaneo de SonarQube: ${e.message}"
    }
}
