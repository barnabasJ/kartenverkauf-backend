#!/usr/bin/env groovy

pipeline {
  agent {
    docker {
      image 'gradle:5.6-jdk11'
      args '--network="host"'
    }
  }

  stages {
    stage('Build') {
      steps {
        echo 'Building...'
        sh 'gradle --no-daemon build'
      }
    }
    stage('Analyze') {
      steps {
        echo 'Building...'
        sh 'gralde --no-daemon sonarqube'
      }
    }
  }
}
