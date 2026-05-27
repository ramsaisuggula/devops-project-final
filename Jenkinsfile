
pipeline {
 agent any
 enviroment {
     AWS_REGION="us-east-1"
     ECR_REPO="703686967391.dkr.ecr.us-east-1.amazonaws.com/sample-app"
 stages {
  
  stage('Checkout') { steps { git 'https://github.com/ramsaisuggula/devops-project-final.git' }}
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
  
stage('Login to AWS ECR') {
            steps {
                sh '''
                aws ecr get-login-password --region $AWS_REGION \
                | docker login --username AWS --password-stdin $ECR_REPO
                '''
            }
        }

    steps {
                sh 'docker push $ECR_REPO:latest'
            }
        }

        stage('Deploy to EKS') {
            steps {
                sh 'kubectl apply -f namespace.yaml'
                sh 'kubectl apply -f deployment.yaml'
                sh 'kubectl apply -f service.yaml'
            }
        }
    }
}

  stage('Docker'){ steps{ script{ docker.build("sample-app:${BUILD_NUMBER}") } } }
 }
}
