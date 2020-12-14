pipeline {
  agent {
    node {
      label 'Jenkinsslave'
    }
  }
  tools {
      maven 'M3'
  }
  environment {
    DOCKER_REGISTRY = 'iad.ocir.io/oraclegilsonmel/msdataworkshop'
  }

  stages {
    stage('Build') {
      steps {
        withMaven (
          maven: 'M3',
          mavenLocalRepo: '.repository',
          mavenSettingsConfig: 'global-settings-xml'
        ) {
            dir ('frontend-helidon') {
              sh 'SCRIPT_DIR=$(dirname $0)'
              sh 'IMAGE_NAME=frontend-helidon'
              sh 'IMAGE_VERSION=0.1'
              sh 'export IMAGE=${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_VERSION}'
              sh 'pwd'
              sh 'ls -ltra'
              sh "mvn install"
              sh "mvn package docker:build"
            }
          }
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