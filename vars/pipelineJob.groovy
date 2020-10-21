#!groovy
def call(Map params) {
    // 让我们来执行个echo命令
    pipeline {
        agent any
        parameters {
            //git代码路径【参数值对外隐藏】
            string(name:'repoUrl', defaultValue: 'git@git.*****.com:*****/*****.git', description: 'git代码路径')
            //repoBranch参数后续替换成git parameter不再依赖手工输入,JENKINS-46451【git parameters目前还不支持pipeline】
            string(name:'repoBranch', defaultValue: 'master', description: 'git分支名称')

        }
        stages {
            stage("第一步") {
                steps {
                    script {
                        echo ${params.name}
                    }
                }
            }
        }
    }

}