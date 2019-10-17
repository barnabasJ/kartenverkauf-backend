#!/usr/bin/env groovy

pipeline {
  agent {
    docker {
      image 'gradle:5.6-jdk11'
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
