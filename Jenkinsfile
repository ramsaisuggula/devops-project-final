
pipeline {
 agent any
 stages {
  stage('Build'){ steps{ sh 'mvn clean package -DskipTests' } }
  stage('Test'){ steps{ sh 'mvn test' } }
  stage('Docker'){ steps{ script{ docker.build("sample-app:${BUILD_NUMBER}") } } }
 }
}
