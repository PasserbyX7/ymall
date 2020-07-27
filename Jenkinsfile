pipeline{
    agent {
        node{
            label "maven"
        }
    }
    parameters{
        string(name:"VERSION_NAME",defaultValue:"1.0.0",description:"版本号")
        string(name:"PROJECT_NAME",defaultValue:"ymall-",description:"项目名")
    }
    environment {
        DOCKER_CREDENTIAL_ID = "aliyun-hub"
        GITHUB_CREDENTIAL_ID = "github"
        KUBECONFIG_CREDENTIAL_ID = "kubeconfig"
        REGISTRY = "registry.cn-shanghai.aliyuncs.com"
        NAMESPACE = "yinn"
        GITHUB_ACCOUNT = "PasserbyX7"
        SONAR_CREDENTIAL_ID = "sonar"
    }
    stages{
        stage("拉取代码"){
            steps{
                checkout(scm)
            }
        }
        stage("单元测试"){
            steps{
                container ("maven") {
                    sh "mvn -gs `pwd`/mvn-settings.xml clean test"
                }
            }
        }
        stage("质量分析"){
            steps {
                container ("maven") {
                    withCredentials([string(credentialsId: "$SONAR_CREDENTIAL_ID", variable: "SONAR_TOKEN")]) {
                        withSonarQubeEnv("sonar") {
                            sh "mvn sonar:sonar -gs `pwd`/mvn-settings.xml -Dsonar.branch=$BRANCH_NAME -Dsonar.login=$SONAR_TOKEN"
                        }
                    }
                    timeout(time: 1, unit: "HOURS") {
                        waitForQualityGate abortPipeline: true
                    }
                }
            }
        }
        stage ("镜像构建与推送") {
            steps {
                container ("maven") {
                    sh "mvn -Dmaven.test.skip=true -gs `pwd`/mvn-settings.xml clean package"
                    sh "docker build -f Dockerfile -t $REGISTRY/$NAMESPACE/$PROJECT_NAME:SNAPSHOT-$BRANCH_NAME-$BUILD_NUMBER $PROJECT_NAME"
                    withCredentials([usernamePassword(passwordVariable : "DOCKER_PASSWORD" ,usernameVariable : "DOCKER_USERNAME" ,credentialsId : "$DOCKER_CREDENTIAL_ID" ,)]) {
                        sh "echo '$DOCKER_PASSWORD' | docker login $REGISTRY -u '$DOCKER_USERNAME' --password-stdin"
                        sh 'docker push  $REGISTRY/$NAMESPACE/$PROJECT_NAME:SNAPSHOT-$BRANCH_NAME-$BUILD_NUMBER'
                    }
                }
            }
        }
        stage('部署到开发环境') {
            when{
                branch 'dev'
            }
            steps {
                kubernetesDeploy(configs: "deploy/dev.yml", enableConfigSubstitution: true, kubeconfigId: "$KUBECONFIG_CREDENTIAL_ID")
            }
        }
        stage('发布正式版镜像'){
            when{
                branch 'master'
                expression{
                    return params.VERSION_NAME =~ /\d+\.\d+\.\d+/
                }
            }
            steps {
                container ('maven') {
                    input(id: 'release-image-with-tag', message: '是否发布正式版镜像？')
                        withCredentials([usernamePassword(credentialsId: "$GITHUB_CREDENTIAL_ID", passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
                            sh 'git config --global user.email "756635176@qq.com" '
                            sh 'git config --global user.name "PasserbyX7" '
                            sh 'git tag -a $VERSION_NAME -m "$VERSION_NAME" '
                            sh 'git push http://$GIT_USERNAME:$GIT_PASSWORD@github.com/$GITHUB_ACCOUNT/devops-multi.git --tags --ipv4'
                    }
                    sh 'docker tag  $REGISTRY/$NAMESPACE/$PROJECT_NAME:SNAPSHOT-$BRANCH_NAME-$BUILD_NUMBER $REGISTRY/$NAMESPACE/$PROJECT_NAME:$VERSION_NAME '
                    sh 'docker push  $REGISTRY/$NAMESPACE/$PROJECT_NAME:$VERSION_NAME'
                }
            }
        }
        stage('部署到生产环境') {
            when{
                branch 'master'
                expression{
                    return params.VERSION_NAME =~ /\d+\.\d+\.\d+/
                }
            }
            steps {
                input(id: 'deploy-to-production', message: '是否部署到生产环境？')
                kubernetesDeploy(configs: "deploy/prod.yml", enableConfigSubstitution: true, kubeconfigId: "$KUBECONFIG_CREDENTIAL_ID")
            }
        }
    }
}