@NonCPS
def call(String abortPipeline) {
    // Obtener el nombre de la rama de Git desde la variable de entorno BRANCH_NAME
    def branchName = env.BRANCH_NAME ?: ''
    
    // Convertir el valor de abortPipeline a booleano
    boolean abort = abortPipeline.toBoolean()
    
    try {
        timeout(time: 5, unit: 'MINUTES') {
            echo "Ejecución de las pruebas de calidad de código"
            sleep 30 // Simulación de tiempo de espera
            
            // Simular resultado exitoso
            def sonarQubeResult = "SUCCESS"
            
            // Determinar si se debe cortar el pipeline
            if (abort || shouldAbortPipeline(branchName)) {
                error "QualityGate de SonarQube no pasó. Abortando el pipeline."
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
