pipeline {
  agent any
  stages {
    stage('Check Environment') {
      parallel {
        stage('Log Tool Version') {
          steps {
            bat '''mvn --version
            git --version
            java -version'''
          }
        }

        stage('Check for POM') {
          steps {
            script {
              if (!fileExists('pom.xml')) {
                error("pom.xml not found!")
              }
            }
          }
        }
      }
    }

    stage('Build with Maven') {
      steps {
        bat 'mvn compile'
      }
    }

    stage('Run Tests') {
      steps {
        bat 'mvn test'  // Changed from 'mvn compile' to 'mvn test'
      }
    }

    stage('Run Static Code Analysis') {
      steps {
        build job: 'static-code-analysis'  // Added quotes
      }
    }

    stage('Create Executable JAR File') {
      steps {
        bat 'mvn package spring-boot:repackage'
      }
    }  

    stage('Build Docker Image') {
      steps {
        bat 'docker build -t cameronmcnz/cams-rps-service .'  // Removed sudo (not recommended in Jenkins)
      }
    }   

    stage('Push Docker Image') {  // Renamed for clarity
      steps {
        bat 'docker push cameronmcnz/cams-rps-service:latest'  // Fixed tag and username
      }
    }

    stage('Deploy to AWS') {
      steps {
        script {
          def response = input(
            message: 'Should we push to DockerHub?', 
            parameters: [
              choice(
                choices: 'Yes\nNo', 
                description: 'Proceed or Abort?', 
                name: 'deployChoice'
              )
            ]
          )
          
          if (response == "Yes") {
            bat 'aws ecs update-service --cluster rps-cluster --service rps-service --force-new-deployment'
          } else {
            writeFile(file: 'deployment.txt', text: 'We did not deploy.')
          }
        }
      }
    }
  }
}