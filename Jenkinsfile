
pipeline {
 agent any
 stages {
  stage('Build'){ steps{ sh 'mvn clean package -DskipTests' } }
  stage('Test'){ steps{ sh 'mvn test' } }
  stage('SonarQube Analysis') {
    environment {
        scannerHome = tool 'sonar-scanner'
    }
    steps {
        withSonarQubeEnv('sonar-server') {
            sh "${scannerHome}/bin/sonar-scanner"
        }
    }
}
  stage("Quality Gate") {
    steps {
        script {
            timeout(time: 1, unit: 'HOURS') {
                def qg = waitForQualityGate()
                if (qg.status != 'OK') {
                    error "Quality Gate Failed"
                }
            }
        }
    }
}
  stage('Docker'){ steps{ script{ docker.build("sample-app:${BUILD_NUMBER}") } } }
 }
}
