
def call(params) {
    // 让我们来执行个echo命令
    pipeline {
        agent any
        stages {
            stage("第一步") {
                steps {
                    script {
                        echo ${params}
                    }
                }
            }
        }
    }

}