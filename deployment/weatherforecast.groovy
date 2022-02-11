#!/usr/bin/env groovy
pipeline {
    agent {
        label 'Node Name'
    }
    tools {
        maven 'Maven 3.6.0'
    }
    stages {
        stage ('Cloning Repository') {
            steps{
                script {
                    try {
                        checkout changelog: false, poll: false, scm: [$class: 'GitSCM', branches: [[name: "master"]]]
                    }catch(Exception e){
                        echo 'Inside catch section of cloning repository SCM..!'
                        currentBuild.result = 'FAILED'
                    }
                }
            }
        }
        stage('Build'){
            steps{
                script{
                    try{

                    }catch(Exception e){
                        echo 'Inside catch section of Build Stage...!'
                        currentBuild.result = 'FAILED'
                    }
                }
            }
        }
        stage('SonarQube Analysis'){
            steps{
                script{
                    try{

                    }catch(Exception e){
                        echo 'Inside catch section of Test Stage...!'
                        currentBuild.result = 'FAILED'
                    }
                }
            }
        }
        stage('Test'){
            steps{
                script{
                    try{

                    }catch(Exception e){
                        echo 'Inside catch section of Test Stage...!'
                        currentBuild.result = 'FAILED'
                    }
                }
            }
        }
        stage('Build And Push Image'){
            steps{
                script{
                    try{

                    }catch(Exception e){
                        echo 'Inside catch section of Test Stage...!'
                        currentBuild.result = 'FAILED'
                    }
                }
            }
        }
    }
}
