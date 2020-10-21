#!groovy
@Library('pipline-libray') _
pipeline {
    agent any
    stages {
        stage("第一步") {
            steps {
                script {
                    pipelineJob("我是一个执行sh命令的例子")
                }
            }
        }
    }
}