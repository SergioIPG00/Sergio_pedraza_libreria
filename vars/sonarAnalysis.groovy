// script: sonarAnalysis.groovy

@NonCPS
def call(boolean abortPipeline) {
    abortPipeline = abortPipeline ?: false
    
    //boolean abort = abortPipeline.toBoolean() // Convertir el valor de String a boolean
    
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
