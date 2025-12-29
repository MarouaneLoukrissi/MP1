pipeline {
    agent any

    tools {
        maven 'Maven3'
        // Remove this line if you haven't configured JDK21 in Jenkins Tools
        // jdk 'JDK21'
    }

    environment {
        DOCKER_IMAGE = "marouaneloukrissi/mp1"
        DOCKER_TAG   = "${BUILD_NUMBER}"
        GIT_MAIN     = "main"
        GIT_TEST     = "test"
    }

    stages {

        stage('Checkout (test branch)') {
            steps {
                checkout scm
                sh '''
                  git fetch --all
                  git checkout $GIT_TEST
                  git pull origin $GIT_TEST
                  git branch --show-current
                '''
            }
        }

        stage('Build & Unit Tests') {
            steps {
                sh 'mvn clean verify'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Code Coverage - JaCoCo') {
            steps {
                sh 'mvn jacoco:report'
            }
            post {
                always {
                    publishHTML(target: [
                        reportDir: 'target/site/jacoco',
                        reportFiles: 'index.html',
                        reportName: 'JaCoCo Coverage'
                    ])
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Merge test -> main') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'GitHub-secret',
                    usernameVariable: 'GH_USER',
                    passwordVariable: 'GH_TOKEN'
                )]) {
                    sh '''
                      git config user.name "$GH_USER"
                      git config user.email "$GH_USER@users.noreply.github.com"

                      git checkout $GIT_MAIN
                      git pull origin $GIT_MAIN
                      git merge $GIT_TEST

                      git remote set-url origin https://$GH_USER:$GH_TOKEN@github.com/MarouaneLoukrissi/MP1.git
                      git push origin $GIT_MAIN
                    '''
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                  docker build -t $DOCKER_IMAGE:$DOCKER_TAG .
                  docker tag $DOCKER_IMAGE:$DOCKER_TAG $DOCKER_IMAGE:latest
                '''
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'Docker-secret',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                      echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                      docker push $DOCKER_IMAGE:$DOCKER_TAG
                      docker push $DOCKER_IMAGE:latest
                    '''
                }
            }
        }
    }

    post {
        success { echo 'Tests validés -> merge main + image Docker pushée' }
        failure { echo 'Pipeline arrêté (tests / qualité non validés)' }
    }
}
