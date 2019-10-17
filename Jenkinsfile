#!/usr/bin/env groovy

pipeline {
  agent {
    docker {
      image 'openjdk:11-alpine'
    }
  }

  stages {
    stage('Build') {
      steps {
        echo 'Building...'
        sh './gradlew build'
      }
    }
  }
}
