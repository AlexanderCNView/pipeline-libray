#!groovy
@Library('pipeline-libray') _
pipeline {
    agent any
    stages {
        stage("第一步") {
            steps {
                script {
                    pipeline("我是一个执行sh命令的例子")
                }
            }
        }
    }
}