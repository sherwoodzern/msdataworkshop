pipeline {
  agent {
    node {
      label 'Jenkinsslave'
      env.PATH = "{tool 'M3'}/bin:${env.Path}"
      configFileProvider(
        [configFile(fileId: 'maven-global-settings', variable: 'MAVEN_SETTINGS')])
    }
  }
  environment {
    DOCKER_REGISTRY = 'iad.ocir.io/oraclegilsonmel/msdataworkshop'
  }

  stages {
    stage('Build') {
      steps {
        git url: 'https://github.com/sherwoodzern/msdataworkshop'
        sh 'cd frontend-helidon'
        sh 'SCRIPT_DIR=$(dirname $0)'
        sh 'IMAGE_NAME=frontend-helidon'
        sh 'IMAGE_VERSION=0.1'
        sh 'export IMAGE=${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_VERSION}'
        withMaven {
          sh "mvn install"
          sh "mvn package docker:build"
        }
        

       sh '''./build.sh'''
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