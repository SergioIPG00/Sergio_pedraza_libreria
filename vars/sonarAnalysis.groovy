@NonCPS
def call(boolean abortPipeline, String branchName) {
        
    try {
        timeout(time: 5, unit: 'MINUTES') {
            echo "Ejecución de las pruebas de calidad de código"
            sleep 30 // Simulación de tiempo de espera
            
            // Simular resultado exitoso
            def sonarQubeResult = "SUCCESS"
            
            // Determinar si se debe cortar el pipeline
            if (abortPipeline || shouldAbortPipeline(branchName)) {
                error "QualityGate de SonarQube no pasó. Abortando el pipeline. ${branchName}"
            }
        }
    } catch (Exception e) {
        error "Error en el escaneo de SonarQube: ${e.message}"
    }
}

// Función para determinar si se debe cortar el pipeline según el nombre de la rama
def shouldAbortPipeline(String branchName) {
    if (branchName == 'master' || branchName.startsWith('hotfix')) {
        return true
    }
    return false
}
