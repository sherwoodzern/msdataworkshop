pipeline {
  agent {
    node {
      label 'Jenkinsslave'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh '''./build.sh


'''
      }
    }

    stage('Test') {
      steps {
        sh 'echo \'Test Services\''
      }
    }

    stage('Deploy') {
      steps {
        sh 'echo \'Deploy services\''
      }
    }

  }
  environment {
    DOCKER_REGISTRY = 'iad.ocir.io/oraclegilsonmel/msdataworkshop'
  }
}