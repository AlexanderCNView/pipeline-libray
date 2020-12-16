#!groovy
def call(Map params) {
    // 让我们来执行个echo命令
    pipeline {
        agent any
        parameters {
            //git代码路径【参数值对外隐藏】
            string(name:'repoUrl', defaultValue: 'https://github.com/AlexanderCNView/pipeline-libray.git', description: 'git代码路径')
            //repoBranch参数后续替换成git parameter不再依赖手工输入,JENKINS-46451【git parameters目前还不支持pipeline】
            gitParameter name: 'BRANCH_TAG',
                    type: 'PT_BRANCH_TAG',
                    branchFilter: 'origin/(.*)',
                    defaultValue: 'main',
                    selectedValue: 'DEFAULT',
                    sortMode: 'DESCENDING_SMART',
                    description: '选择分支或者tag'

        }
        stages {
            stage('代码拉取') {
                steps {
                    script{
                        checkout([$class: 'GitSCM', branches: [[name: "${BRANCH_TAG}"]],
                                  doGenerateSubmoduleConfigurations: false, extensions: [],
                                  submoduleCfg: [], userRemoteConfigs: [[credentialsId: '6ef41d8b-2079-4ba1-bb68-f1a50f68853c',
                                                                         url: '${repoUrl}']]])
                        echo "checkout from ${BRANCH_TAG}"
                        echo "${params.name}"
                        msg = getChangeString()
                        echo "${msg}"
                    }
                }
            }
        }
    }

}

@NonCPS
def getChangeString() {
    MAX_MSG_LEN = 100
    def changeString = ""
    echo "Gathering SCM changes"
    def changeLogSets = currentBuild.changeSets
    for (int i = 0; i < changeLogSets.size(); i++) {
        def entries = changeLogSets[i].items
        for (int j = 0; j < entries.length; j++) {
            def entry = entries[j]
            truncated_msg = entry.msg.take(MAX_MSG_LEN)
            changeString += "--${truncated_msg}  [${entry.author}]\n"
        }
    }

    if (!changeString) {
        changeString = " - 无"
    }
    return changeString
}