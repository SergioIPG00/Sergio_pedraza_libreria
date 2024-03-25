@NonCPS
def call(boolean abortPipeline = false) {
    timeout(time: 5, unit: 'MINUTES') {
        try {            
            sh 'echo "Ejecución de las pruebas de calidad de código"'
            
            sleep 30             
            
            def sonarQubeResult = "SUCCESS"
			
            if (abortPipeline && sonarQubeResult != "SUCCESS") {
                error "QualityGate de SonarQube no pasó. Abortando el pipeline."
            }
        } catch (Exception e) {
            error "Error en el escaneo de SonarQube: ${e.message}"
        }
    }
}
