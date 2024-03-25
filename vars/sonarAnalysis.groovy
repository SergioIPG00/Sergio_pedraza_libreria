// script: sonarAnalysis.groovy

@NonCPS
def call(String abortPipeline) {
    abortPipeline = abortPipeline ?: "false"
    
    boolean abort = abortPipeline.toBoolean() // Convertir el valor de String a boolean
    
    try {
        timeout(time: 5, unit: 'MINUTES') {
            echo "Ejecución de las pruebas de calidad de código"
            sleep 30 // Simulación de tiempo de espera
            
            // Simular resultado exitoso
            def sonarQubeResult = "SUCCESS"
            
            if (abort && sonarQubeResult != "SUCCESS") {
                error "QualityGate de SonarQube no pasó. Abortando el pipeline."
            }
        }
    } catch (Exception e) {
        error "Error en el escaneo de SonarQube: ${e.message}"
    }
}
