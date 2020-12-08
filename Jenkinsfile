pipeline {
  agent any
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
}